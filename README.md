# IoTSystem

## Phần cứng

### Sơ đồ mạch

- <img src="https://github.com/user-attachments/assets/69e48834-7f22-40e3-a9eb-df7772536646" alt="Sơ đồ mạch 1" width="600px" />
- <img src="https://github.com/user-attachments/assets/d509a8b9-f6c0-4c4c-907a-a7a35904c9f5" alt="Sơ đồ mạch 2" width="600px" />


### Danh sách các thiết bị
| STT | Tên thiết bị           | Số lượng   |
|-----|------------------------|------------|
| 1   | Dây điện 0.5m          | 2          |
| 2   | Bóng đèn 12V           | 2          |
| 3   | ESP32                  | 1          |
| 4   | UPS 12V                | 1          |
| 5   | DHT22                  | 1          |
| 6   | Servo                  | 1          |
| 7   | Relay 12V              | 3          |
| 8   | Chốt điện 12V          | 1          |
| 9   | GP2Y1010AU0F           | 1          |
| 10  | Tua vít                | 1          |
| 11  | Breadboard             | 1          |
| 12  | Keo nến                | 10         |
| 13  | Súng bắn keo nến       | 1          |
| 14  | Xốp Formex 2m²         | 2          |
| 15  | Dao cắt / Dao rọc giấy | 1          |

## Phần mềm:
- Web:
  - Front-end: HTML, CSS
  - Back-end: Java Spring Boot
  <img src="https://github.com/user-attachments/assets/9c412839-fdb0-4835-b1ed-1b700a74e049" alt="Phần mềm Web" width="500px" />
- Lập trình phần cứng: Arduino IDE, ...


## Kịch bản.
• Tổng quan hệ thống:
– Trình bày và mô phỏng cách các thiết bị IOT và web service tương tác với
nhau.
– Vai trò của các thiết bị iot là thu thập dữ liệu từ môi trường và web service sẽ chịu trách nhiệm xử lý
• Các thành phần chính:
– Esp32
– Sensor
– WebService
• Luồng dữ liệu:
– Từ sensor đến Esp32: esp32 sẽ đọc dữ liệu và xử lý nếu cần.
– Từ các thiết bị iot đến webservice: gửi dữ liệu thông qua các phương thức
(https hoặc mqtt).
– Web service cập nhật dữ liệu vào database.

## Demo ứng dụng.

### Phần cứng:
- <img src="https://github.com/user-attachments/assets/be2311c4-3bc2-4c20-9b23-c0cc4e88fd6e" alt="Phần cứng 1" width="500px" />
- <img src="https://github.com/user-attachments/assets/7c9d310c-e29d-47b8-9941-69b9dd3c7bd0" alt="Phần cứng 2" width="500px" />
- <img src="https://github.com/user-attachments/assets/de604d79-d6c0-4344-9681-186f6639a155" alt="Phần cứng 3" width="500px" />
- <img src="https://github.com/user-attachments/assets/c6a9174c-e2a1-47ef-b3f5-5ba02169080d" alt="Phần cứng 4" width="500px" />
- <img src="https://github.com/user-attachments/assets/c65a4faf-e7d5-428c-9cae-06bccfd0e40b" alt="Phần cứng 5" width="500px" />

### Phần mềm:
- <img src="https://github.com/user-attachments/assets/29d63987-17e8-4211-ba73-c08c1c2026b9" alt="Phần mềm" width="500px" />



