from flask import Flask, request, jsonify
import random

app = Flask(__name__)

# Mock Data
MARKET_PRICES = [
    {"crop": "Wheat", "price": 2100, "trend": "up", "forecast": 2150},
    {"crop": "Rice", "price": 1950, "trend": "stable", "forecast": 1950},
    {"crop": "Cotton", "price": 6200, "trend": "down", "forecast": 6100},
    {"crop": "Sugarcane", "price": 305, "trend": "up", "forecast": 310}
]

@app.route('/api/auth/login', methods=['POST'])
def login():
    data = request.json
    # Mock login - accept any phone/password
    return jsonify({"success": True, "token": "mock_token_123", "user": {"name": "Kisan Bhai", "phone": data.get('phone')}})

@app.route('/api/auth/signup', methods=['POST'])
def signup():
    return jsonify({"success": True, "message": "User registered successfully"})

@app.route('/api/predict-disease', methods=['POST'])
def predict_disease():
    # Mock disease prediction
    diseases = ["Leaf Rust", "Powdery Mildew", "Healthy", "Blight"]
    result = random.choice(diseases)
    confidence = random.randint(70, 99)
    return jsonify({"disease": result, "confidence": confidence, "treatment": "Apply fungicide if severe."})

@app.route('/api/market-prices', methods=['GET'])
def get_market_prices():
    return jsonify(MARKET_PRICES)

@app.route('/api/weather', methods=['GET'])
def get_weather():
    # Mock weather data
    return jsonify({
        "temp": 28,
        "condition": "Partly Cloudy",
        "humidity": 65,
        "wind_speed": 12,
        "forecast": [
            {"day": "Mon", "temp": 29, "icon": "sun"},
            {"day": "Tue", "temp": 27, "icon": "cloud"},
            {"day": "Wed", "temp": 30, "icon": "sun"},
            {"day": "Thu", "temp": 26, "icon": "rain"},
            {"day": "Fri", "temp": 28, "icon": "cloud"}
        ]
    })

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
