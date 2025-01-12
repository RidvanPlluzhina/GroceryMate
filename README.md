# 🛒Grocery Mate

Grocery Mate is an Android application developed as part of the *Engineering of Mobile Systems* course under the supervision of Professor **Niccolò Pretto** at the *Free University of Bozen-Bolzano*. The app aims to assist users in managing their groceries with an intuitive interface, personalized lists, and other user-friendly features.

---

## ✨Features

### 1. 🗂Categorized Product Display
- Products are fetched using the **Open Food Facts API** and displayed in categories like:
  - **Drinks**
  - **Sweets**
  - **Snacks**
  - **Milk Products**
  - **Bio Products**

### 2. 🛍️Shopping Cart
- Users can add products to a **virtual cart** for easy tracking of their grocery lists.
- Products in the cart can be **reviewed or removed**.

### 3. ❤️Favorite Products
- Users can save their favorite products for future reference in the **Favourites** section.

### 4. 🍎Calorie Counter
- Displays **nutritional information** such as:
  - 🔥 **Calories**
  - 🍬 **Sugar**
  - 🥩 **Protein**
  - 🧈 **Fat**
- Provides a **summary of calories** and nutrients for selected items.

### 5. ✍️Reviews
- Users can write, save, and view reviews for various products.

---

## 🛠️Technical Details

### 1. 🏛️App Architecture
- Implements the **MVVM Architecture**:
  - **Model**: Room Database (local data persistence)
  - **View**: Jetpack Compose UI components
  - **ViewModel**: Manages app state and logic

### 2. 📂Internal Database
- **Room** is used for managing:
- 🛒 Shopping cart
- ❤️ Favorite items
- 📝 Product reviews

### 3. 🌐REST API Integration
- The app uses the **Open Food Facts API** to fetch data about products based on their **barcode**.  
  **API Documentation**: [Open Food Facts API](https://world.openfoodfacts.org/)

### 4. Navigation
- Implements **Jetpack Navigation** for seamless transitions between screens:
  - 🏠 **Home**
  - 🥤 **Drinks**
  - 🍫 **Sweets**
  - 🛍️ **Cart**
  - ❤️ **Favourites**
  - ✍️ **Reviews**

### 5. 🎨Design
- Follows **Material Design Guidelines**:
  - Consistent UI elements
  - Intuitive navigation and layout

---
## 🧰Installation & Setup

### ✅Prerequisites
- Android Studio (latest version recommended)
- Kotlin support
- Internet connection for API calls

### 🚀Steps
1. Clone the repository:
   ```bash
   git clone https://gitlab.inf.unibz.it/2324-engineeringofmobilesystems/plluzhina_ridvan-grocery-mate.git

2. Open the project in Android Studio.
3. Build and run the app on an emulator or a physical device.
---

## 🖥️How to Use

- Navigate through different categories like **Drinks**, **Snacks**, and more.
- Add items to your **Cart** or save them as **Favorites**.
- Review **nutritional information** or write **reviews** for products.
- Use the **Calorie Counter** to keep track of your intake.

---

## 📁App Architecture Overview

### Folder Structure
- **model**: Contains data models and database classes.
- **network**: Contains API services and Retrofit configurations.
- **userinterface**: UI components and screen implementations.
- **viewmodel**: ViewModels managing app logic and state.

### ⚙️Key Technologies Used
- ** 🖌️Jetpack Compose**: Modern UI toolkit.
- ** 🗂️Room Database**: Local storage for user data.
- ** 🌐Retrofit**: REST API integration.
- ** ☕Kotlin**: Main programming language.

---

## 📄Documentation
The following project documentation is included in the repository:
- 📄Report: A detailed explanation of the app’s architecture, features, and implementation.
- 📊Presentation Slides: A concise visual overview of the project. Both files are located in the Documentation folder.

---

## 📅Current Status

- Project Deadline (Report and Presentation): **22nd January**
- Project Demo and Presentation: **29th January**

---

## 🙋Contributor

**Ridvan Plluzhina**  
Student at the Faculty of Engineering, Free University of Bozen-Bolzano.

---

## ✅Requirements Checklist

- ✅ App Architecture (ViewModel, Room Database, etc.)
- ✅ Material Design with Jetpack Compose
- ✅ REST API Integration with Retrofit
- ✅ Internal Database using Room
- ✅ Navigation Bar Implementation
- ✅ Categorized Product Display
- ✅ Cart and Favorites Management

---

## 🙏Acknowledgments

Special thanks to **Professor Niccolò Pretto** for guidance and support throughout the project.