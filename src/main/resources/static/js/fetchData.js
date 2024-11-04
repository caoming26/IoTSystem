//chart
const temperatureChart = document.getElementById("temperatureChart");
const humidityChart = document.getElementById("humidityChart");
const dustChart = document.getElementById("dustChart");
let tempChart1, humidityChart1, dustChart1;




//api local
const temperatureApi= "/api/report/temperature"; // URL của API
const humidityApi = "/api/report/humidity";
const dustApi = "/api/report/dust";

function createHistoricalChart(canvasId, label) {
    const ctx = document.getElementById(canvasId).getContext('2d');
    return new Chart(ctx, {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: label,
                data: [],
                borderColor: '#3f51b5',
                tension: 0.1
            }]
        },
        options: {
            plugins: {
                title: {
                    display: true, // Bật hiển thị tiêu đề
                    text: 'Monthly Sales Data', // Nội dung tiêu đề
                    font: {
                        size: 10 // Kích thước font tiêu đề
                    },
                    color: '#333', // Màu chữ của tiêu đề
                    padding: {
                        top: 2,
                        bottom: 5
                    },
                    align: 'center' // Căn lề tiêu đề (center, start, end)
                }
            },
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: true
                }
            },
            // plugins: {
            //     legend: {
            //         display: false
            //     }
            // }
        }
    });
}

function createGauge(canvasId, options) {
    const gauge = new Gauge(document.getElementById(canvasId));
    gauge.setOptions(options);
    gauge.maxValue = options.maxValue;
    gauge.setMinValue(options.minValue);
    gauge.animationSpeed = 32;
    gauge.set(0);
    return gauge;
}
const temperatureGauge1 = createGauge('temperatureGauge', {
    maxValue: 40,
    minValue: -30,
    angle: -0.2,
    lineWidth: 0.2,
    radiusScale: 0.9,
    pointer: {
        length: 0.6,
        strokeWidth: 0.035
    },
    limitMax: false,
    limitMin: false,
    colorStart: '#6FADCF',
    colorStop: '#8FC0DA',
    strokeColor: '#E0E0E0',
    generateGradient: true,
    highDpiSupport: true
});
const humidityGauge1 = createGauge('humidityGauge', {
    maxValue: 50,
    minValue: -50,
    angle: -0.2,
    lineWidth: 0.2,
    radiusScale: 0.9,
    pointer: {
        length: 0.6,
        strokeWidth: 0.035
    },
    limitMax: false,
    limitMin: false,
    colorStart: '#6FADCF',
    colorStop: '#8FC0DA',
    strokeColor: '#E0E0E0',
    generateGradient: true,
    highDpiSupport: true
});
const dustGauge1 = createGauge('dustGauge', {
    maxValue: 500,
    minValue: -750,
    angle: -0.2,
    lineWidth: 0.2,
    radiusScale: 0.9,
    pointer: {
        length: 0.6,
        strokeWidth: 0.035
    },
    limitMax: false,
    limitMin: false,
    colorStart: '#6FADCF',
    colorStop: '#8FC0DA',
    strokeColor: '#E0E0E0',
    generateGradient: true,
    highDpiSupport: true
});
// Hàm cập nhật biểu đồ
function updateTempChart(data) {
    // const timestamps = data.map(item => new Date(item.time).toLocaleTimeString()); // Chuyển đổi thời gian
    let timestamps =  new Date(data.time).toLocaleTimeString(); // Chuyển đổi thời gian
    // const temperatures = data.map(item => item.temperature); // Lấy giá trị nhiệt độ
    let temperatures = data.temperature; // Lấy giá trị nhiệt độ
    // const dust = data.map(item => item.dust); // Lấy giá trị nhiệt độ
    // const humidity = data.map(item => item.humidity); // Lấy giá trị nhiệt độ

    tempChart1.data.labels.push(timestamps); // Gán nhãn cho biểu đồ
    tempChart1.data.datasets[0].data.push(temperatures); // Gán dữ liệu cho biểu đồ

    if (tempChart1.data.labels.length > 10) {
        tempChart1.data.labels.shift();
        tempChart1.data.datasets[0].data.shift();
    }

    tempChart1.update(); // Cập nhật biểu đồ
    temperatureGauge1.set(temperatures)
}
function updateHumidChart(data) {
    // const timestamps = data.map(item => new Date(item.time).toLocaleTimeString()); // Chuyển đổi thời gian
    let timestamps =  new Date(data.time).toLocaleTimeString(); // Chuyển đổi thời gian
    // const temperatures = data.map(item => item.temperature); // Lấy giá trị nhiệt độ
    const humidity = data.humidity; // Lấy giá trị nhiệt độ
    // const dust = data.map(item => item.dust); // Lấy giá trị nhiệt độ
    // const humidity = data.map(item => item.humidity); // Lấy giá trị nhiệt độ

    humidityChart1.data.labels.push(timestamps); // Gán nhãn cho biểu đồ
    humidityChart1.data.datasets[0].data.push(humidity); // Gán dữ liệu cho biểu đồ

    if (humidityChart1.data.labels.length > 10) {
        humidityChart1.data.labels.shift();
        humidityChart1.data.datasets[0].data.shift();
    }

    humidityChart1.update(); // Cập nhật biểu đồ
    humidityGauge1.set(humidity)
}
function updateDustChart(data) {
    // const timestamps = data.map(item => new Date(item.time).toLocaleTimeString()); // Chuyển đổi thời gian
    let timestamps =  new Date(data.time).toLocaleTimeString(); // Chuyển đổi thời gian
    // const temperatures = data.map(item => item.temperature); // Lấy giá trị nhiệt độ
    let dust = data.dust; // Lấy giá trị nhiệt độ
    // const dust = data.map(item => item.dust); // Lấy giá trị nhiệt độ
    // const humidity = data.map(item => item.humidity); // Lấy giá trị nhiệt độ

    dustChart1.data.labels.push(timestamps); // Gán nhãn cho biểu đồ
    dustChart1.data.datasets[0].data.push(dust); // Gán dữ liệu cho biểu đồ

    if (dustChart1.data.labels.length > 10) {
        dustChart1.data.labels.shift();
        dustChart1.data.datasets[0].data.shift();
    }

    dustChart1.update(); // Cập nhật biểu đồ
    dustGauge1.set(dust)
}


//fetch data
async function fetchData() {
    try {
        // const response = await fetch(apiUrl);
        const [temperatureResponse, humidityResponse, dustResponse] = await Promise.all([
            fetch(temperatureApi),
            fetch(humidityApi),
            fetch(dustApi)
        ]);
        // Kiểm tra phản hồi từ từng API
        if (!temperatureResponse.ok) {
            throw new Error('Network response was not ok for temperature: ' + temperatureResponse.statusText);
        }
        if (!humidityResponse.ok) {
            throw new Error('Network response was not ok for humidity: ' + humidityResponse.statusText);
        }
        if (!dustResponse.ok) {
            throw new Error('Network response was not ok for dust: ' + dustResponse.statusText);
        }

        // Chuyển đổi dữ liệu JSON từ các phản hồi
        const temperatureData = await temperatureResponse.json();
        const humidityData = await humidityResponse.json();
        const dustData = await  dustResponse.json();

        // Xử lý dữ liệu nhiệt độ
        if (temperatureData.length > 0) {
            const firstTemperatureValue = temperatureData[0]; // Lấy giá trị nhiệt độ đầu tiên
            console.log(firstTemperatureValue);
            updateTempChart(firstTemperatureValue); // Gọi hàm để hiển thị kết quả
        } else {
            console.error('Không có dữ liệu nào trong mảng nhiệt độ.');
        }

        // Xử lý dữ liệu độ ẩm
        if (humidityData.length > 0) {
            const firstHumidityValue = humidityData[0]; // Lấy giá trị độ ẩm đầu tiên
            console.log(firstHumidityValue);
            updateHumidChart(firstHumidityValue); // Gọi hàm để hiển thị kết quả độ ẩm (hàm updateHumidityChart cần được định nghĩa)
        } else {
            console.error('Không có dữ liệu nào trong mảng độ ẩm.');
        }

        // Xử lý dữ liệu độ bụi
        if (dustData.length > 0) {
            const firstDustValue = dustData[0]; // Lấy giá trị độ ẩm đầu tiên
            console.log(firstDustValue);
            updateDustChart(firstDustValue); // Gọi hàm để hiển thị kết quả độ ẩm (hàm updateHumidityChart cần được định nghĩa)
        } else {
            console.error('Không có dữ liệu nào trong mảng độ ẩm.');
        }


    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}



// Khởi tạo biểu đồ và lấy dữ liệu khi trang tải
window.onload = async function () {
    tempChart1 = createHistoricalChart('temperatureChart', 'Temperature'); // Tạo biểu đồ
    humidityChart1 = createHistoricalChart('humidityChart', 'Humidity'); // Tạo biểu đồ
    dustChart1 = createHistoricalChart('dustChart', 'Dust'); // Tạo biểu đồ

    await fetchData(); // Gọi hàm lấy dữ liệu và chờ kết quả

    setInterval(fetchData, 5000);
};

// async function fetchData() {
//     try {
//         // const response = await fetch(apiUrl);
//         const response = await fetch("/api/report/temperature");
//         if (!response.ok) {
//             throw new Error('Network response was not ok: ' + response.statusText);
//         }
//         const data = await response.json();
//         if (data.length > 0) {
//             const firstValue = data[0]; // Lấy giá trị nhiệt độ đầu tiên
//             console.log((firstValue))
//             updateChart(firstValue); // Gọi hàm để hiển thị kết quả
//         } else {
//             console.error('Không có dữ liệu nào trong mảng.');
//         }
//         // displayData(data); // Gọi hàm để hiển thị dữ liệu
//     } catch (error) {
//         console.error('There was a problem with the fetch operation:', error);
//     }
// }
