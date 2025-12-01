# AgriDost 🌾

AgriDost is a comprehensive Android application designed to empower farmers with technology. It serves as a digital companion, providing real-time information and AI-driven assistance to improve agricultural productivity and decision-making.

## 🚀 Features

*   **🤖 AI Assistant (Kisan Sahayak)**: A conversational AI powered by **Google Gemini** to answer farming-related queries in natural language. Supports voice input and text-to-speech output.
*   **🍂 Disease Detection**: (Mock) Identify crop diseases and get treatment recommendations.
*   **💰 Market Prices**: View current market prices (Mandi rates) for various crops with trend indicators.
*   **Vm Weather Updates**: Get real-time weather information and forecasts to plan farming activities.
*   **🔐 User Authentication**: Secure login and signup using **Firebase Authentication**.

## 🛠️ Tech Stack

### Android App (Frontend)
*   **Language**: Java
*   **UI**: XML Layouts, Material Design
*   **Networking**: Retrofit
*   **Image Loading**: Glide
*   **AI Integration**: Google Generative AI SDK (Gemini)
*   **Authentication**: Firebase Auth

### Backend
*   **Language**: Python
*   **Framework**: Flask
*   **Purpose**: Serves mock data for market prices, weather, and disease prediction.

## ⚙️ Setup Instructions

### Prerequisites
*   Android Studio (latest version recommended)
*   Python 3.x installed on your machine

### 1. Backend Setup
The backend is a simple Flask server that provides data to the app.

1.  Navigate to the `backend` directory:
    ```bash
    cd backend
    ```
2.  (Optional) Create a virtual environment:
    ```bash
    python -m venv .venv
    # Windows
    .venv\Scripts\activate
    # Mac/Linux
    source .venv/bin/activate
    ```
3.  Install dependencies:
    ```bash
    pip install -r requirements.txt
    ```
4.  Run the server:
    ```bash
    python app.py
    ```
    The server will start at `http://0.0.0.0:5000`. Ensure your Android device/emulator can reach your machine's IP address if not using `localhost` via `adb reverse`.

### 2. Android App Setup
1.  Open the project in **Android Studio**.
2.  Sync the project with Gradle files.
3.  **Configure API Keys**:
    *   Open `app/src/main/java/com/example/agridost/AssistantActivity.java`.
    *   Replace the `GEMINI_API_KEY` placeholder with your actual Google Gemini API Key.
4.  **Firebase Setup**:
    *   Ensure `google-services.json` is present in the `app/` directory. If not, download it from your Firebase Console and place it there.
5.  **Run the App**:
    *   Connect an Android device or start an emulator.
    *   Click the **Run** button in Android Studio.

## 📱 Usage
*   **Login/Signup**: Create an account or log in to access features.
*   **Assistant**: Tap the mic button to speak to Kisan Sahayak or type your questions.
*   **Dashboard**: Access Market Prices, Weather, and Disease Detection from the home screen.

## 🤝 Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## 📄 License
[MIT License](LICENSE)
