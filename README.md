# Retail android application

### Introduction

  - Android app to display product items in a view and add them to cartx

### Implementation
#### Data layer
The database is created using Room and has one entity: a `ProductCart`. Room generates the corresponding SQLite table at runtime.
Queries are executed in the `ProductCartDao` class. The user retrieval is done via query implemented using LiveData. Every time the user data is updated, the LiveData object will emit automatically, allowing to update the UI based on the latest data. 

### Presentation layer

The app has a main Activity that displays the data. The Activity works with a ViewModel to do the following:

- Product and categories are displayed in a single recycler view. The adapter handles heterogeneous view holders for product and category.
- Product added to cart is inserted to the database in the RetailLandingViewModel.
- CartViewModel subscribe to the emissions of the product and update the UI every time there is a new product emitted

Room guarantees that the observable query will be triggered on a background thread. In the Activity, the LiveData events are set to be received on the main thread, so the UI can be updated. The insert query is synchronous so it's wrapped in a Async and executed on a background thread. On completion, the Activity is notified on the main thread.

### Tech Stack
- Android
- Java
- Room DB
- MVVM
- Constraint layout
- Sectional Recycler View





