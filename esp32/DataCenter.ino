#include "DHT.h"
#include <SPI.h>
#include <MFRC522.h>
#include <ESP32Servo.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <WiFi.h>
#include <HTTPClient.h>

// OLED
#define SCREEN_WIDTH 128
#define SCREEN_HEIGHT 64
Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, -1);

// RFID
#define SS_PIN 5
#define RST_PIN 4
MFRC522 rfid(SS_PIN, RST_PIN);
MFRC522::MIFARE_Key key;

// Relay pins
#define IN1 16 // Light
#define IN2 17 // Light
#define IN3 14 // Gas alarm
#define IN4 15 // Door lock

// Servo
#define SERVO_PIN 26
Servo servoMotor;

const int DHTPIN = 27;
const int DHTTYPE = DHT11;
DHT dht(DHTPIN, DHTTYPE);

const int MQ2pin = 32;

float sensorValue;  // MQ2 sensor value

// Wi-Fi credentials
const char* ssid = "2205";
const char* password = "123456790";

unsigned long lastSendTime = 0;
const unsigned long sendInterval = 1000; // 1 second

void printHex(byte *buffer, byte bufferSize);
void printDec(byte *buffer, byte bufferSize);
void sendData(String type, float value);

void setup() {
    Serial.begin(9600);
    SPI.begin();
    dht.begin();
    rfid.PCD_Init();
    
    servoMotor.attach(SERVO_PIN); 
    servoMotor.write(0); 

    pinMode(IN1, OUTPUT);
    pinMode(IN2, OUTPUT);
    pinMode(IN3, OUTPUT);
    pinMode(IN4, OUTPUT);
    
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, HIGH);
    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, HIGH);
    
    delay(1000);

    if (!display.begin(SSD1306_SWITCHCAPVCC, 0x3C)) {
        Serial.println(F("OLED không thể khởi động"));
        for (;;);
    }
    display.clearDisplay();
    display.setTextSize(1);
    display.setTextColor(WHITE);
    display.setCursor(0, 0);
    display.print("Nhom 6.2");
    display.display();
    
//    WiFi.begin(ssid, password);
//    Serial.println("Đang kết nối Wi-Fi...");
//    while (WiFi.status() != WL_CONNECTED) {
//        delay(1000);
//        Serial.print(".");
//    }
//    Serial.println("Kết nối Wi-Fi thành công!");
//    Serial.print("IP: ");
//    Serial.println(WiFi.localIP());
}

void loop() {
    sensorValue = analogRead(MQ2pin);
    Serial.print("Sensor Value: ");
    Serial.println(sensorValue);

    float h = dht.readHumidity();
    float t = dht.readTemperature();

    Serial.print("Nhiệt độ: ");
    Serial.println(t);
    Serial.print("Độ ẩm: ");
    Serial.println(h);
    Serial.println();

    display.clearDisplay();
    display.setCursor(0, 0);
    display.print("Nhom 12");

    display.setCursor(0, 10);
    display.print("Nhiet do: ");
    display.print(t);
    display.print("C");

    display.setCursor(0, 20);
    display.print("Do am: ");
    display.print(h);
    display.print("%");

//    unsigned long currentMillis = millis();
//    if (currentMillis - lastSendTime >= sendInterval) {
//        // Gửi tất cả dữ liệu sau mỗi 1 giây
//        sendData("temperature", t);
//        sendData("humidity", h);
//        sendData("dust", sensorValue);
//
//        lastSendTime = currentMillis;
//    }

    bool rfidDetected = false;

    if (rfid.PICC_IsNewCardPresent() && rfid.PICC_ReadCardSerial()) {
        rfidDetected = true;
        Serial.println(F("\nThẻ RFID được quét:"));

        Serial.print("UID thẻ (Hex): ");
        printHex(rfid.uid.uidByte, rfid.uid.size);
        Serial.println();

        Serial.print("UID thẻ (Dec): ");
        printDec(rfid.uid.uidByte, rfid.uid.size);
        Serial.println();

//        sendRFIDData(rfid.uid.uidByte, rfid.uid.size);

        rfid.PICC_HaltA();
        rfid.PCD_StopCrypto1();
        
        servoMotor.write(80); 
        digitalWrite(IN4, LOW);
        digitalWrite(IN1, LOW); 

        display.setCursor(0, 30);
        display.print("Trang thai cua: Mo ");
        display.display();
        delay(1000);         
        servoMotor.write(0);
        delay(1000);
        digitalWrite(IN4, HIGH);
        digitalWrite(IN1, HIGH);
    }

    if (!rfidDetected) {
        display.setCursor(0, 30);
        display.print("Trang thai cua: Dong");
        Serial.println("Không phát hiện thẻ RFID.");
    }

    if (t > 33) {
        digitalWrite(IN2, LOW); 
    } else {
        digitalWrite(IN2, HIGH);
    }

    if (sensorValue > 2000) {
        digitalWrite(IN3, LOW);
    } else {
        digitalWrite(IN3, HIGH); 
    }

    display.display();
    Serial.println("====================================\n");
    delay(1000);
}

// Function to send sensor data
void sendData(String type, float value) {
    if (WiFi.status() == WL_CONNECTED) {
        HTTPClient http;
        String url = "https://iotsystem-production.up.railway.app/api/report/add";
        String body = "{\"type\":\"" + type + "\",\"" + type + "\":" + String(value, 2) + "}";

        http.begin(url);  // Bắt đầu yêu cầu HTTP
        http.addHeader("Content-Type", "application/json");  // Thêm header cho yêu cầu

        http.POST(body);  // Gửi dữ liệu

        http.end();  // Kết thúc kết nối HTTP
    } else {
        Serial.println("Mất kết nối Wi-Fi");
    }
}

void sendRFIDData(byte *rfidUID, byte bufferSize) {
    HTTPClient http;
    String url = "https://iotsystem-production.up.railway.app/api/visit-history";
    String rfidStr;

    // Chuyển UID của thẻ RFID thành chuỗi dạng hex
    for (byte i = 0; i < bufferSize; i++) {
        if (rfidUID[i] < 0x10) rfidStr += "0";  // Đảm bảo mỗi byte có 2 chữ số hex
        rfidStr += String(rfidUID[i], HEX);
        if (i < bufferSize - 1) rfidStr += " ";  // Thêm khoảng cách giữa các byte
    }
    rfidStr.toUpperCase();  // Chuyển chuỗi thành chữ in hoa cho dễ đọc

    // Tạo body JSON với UID của thẻ RFID
    String body = "{\"rfid\":\"" + rfidStr + "\"}";
    http.begin(url);
    http.addHeader("Content-Type", "application/json");

    // Gửi yêu cầu POST
    int httpResponseCode = http.POST(body);
    if (httpResponseCode > 0) {
        Serial.print("Gửi RFID thành công, mã phản hồi: ");
        Serial.println(httpResponseCode);
    } else {
        Serial.print("Lỗi khi gửi RFID, mã lỗi: ");
        Serial.println(httpResponseCode);
    }

    http.end();
}


// Function to print UID in Hex format
void printHex(byte *buffer, byte bufferSize) {
    for (byte i = 0; i < bufferSize; i++) {
        Serial.print(buffer[i] < 0x10 ? "0" : "");
        Serial.print(buffer[i], HEX);
        if (i < bufferSize - 1) Serial.print(" ");
    }
}

// Function to print UID in Decimal format
void printDec(byte *buffer, byte bufferSize) {
    for (byte i = 0; i < bufferSize; i++) {
        Serial.print(buffer[i], DEC);
        if (i < bufferSize - 1) Serial.print("-");
    }
}
