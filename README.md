# Spending Tracker

A simple Java-based Spending Tracker application to record and manage daily expenses. This project allows users to add, view, and delete spending records, as well as categorize expenses.

## Features

- **Add/View Categories**: Users can create and view categories for different types of expenses.
- **Track Spending**: Track your expenses for the last 20 days.
- **Total Spending**: Displays the total amount spent within the last 20 days.
- **Delete Spending Entries**: Allows users to delete spending records.
- **User-Friendly Interface**: Built using Java Swing for a simple GUI interface.

## Technologies Used

- Java
- Swing (for GUI)
- Oracle Database (for data storage)
- JDateChooser (for date selection)

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) version 8 or higher.
- Oracle Database setup (with the required tables)
- Eclipse IDE or any Java IDE of your choice.

### Steps to Run the Project

1. **Clone the Repository**:
   - Clone the repository to your local machine using the following command:
     ```bash
     git clone https://github.com/yourusername/spending-tracker.git
     ```

2. **Import into Eclipse**:
   - Open Eclipse IDE.
   - Go to `File` > `Import`.
   - Select `Existing Projects into Workspace`.
   - Browse to the cloned project folder and click `Finish`.

3. **Run the Project**:
   - In Eclipse, locate the `SpendingTracker.java` file in the `src` folder.
   - Right-click on `SpendingTracker.java` and select `Run As` > `Java Application`.

4. **Database Setup**:
   - The project uses an Oracle database to store spending records and categories.
   - Make sure the database is set up and running.
   - You can modify the database connection details in the `DbConnect.java` file if necessary.

### Database Schema

The project uses two tables in the database:

1. **category_info**: Stores categories for expenses.
   - `category` (VARCHAR): The name of the category.

2. **spendings**: Stores the spending records.
   - `sid` (INTEGER): The unique ID for each spending record.
   - `category` (VARCHAR): The category of the spending.
   - `sdate` (DATE): The date of the spending.
   - `amount` (INTEGER): The amount spent.

### Running the Application

- After running the application, the user can:
  - **Add a new category** by entering the category name and clicking "ADD".
  - **Add a new spending record** by selecting a category, entering the amount, and selecting the date.
  - **View all spendings** for the last 20 days.
  - **Delete a spending record** by selecting it from the table and clicking "REMOVE".

## Contributing

If you would like to contribute to this project, feel free to fork the repository and create a pull request with your changes.

## License

This project is open-source and available under the MIT License.

## About the Developer

Developed by Hari Keerthana.

## Acknowledgments

- Thanks to all the open-source libraries and tools used in this project.

