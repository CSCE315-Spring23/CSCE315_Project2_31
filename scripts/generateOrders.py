import csv
import random
import datetime
import numpy as np


menu = [
    "Classic Chicken Sandwich",
    "Spicy Chicken Sandwich",
    "Chicken Deluxe Sandwich",
    "Grilled Chicken Sandwich",
    "Grilled Chicken Club Sandwich",
    "Cool Wrap",
    "Nuggets",
    "Chick-n-Strips",
    "Cobb Salad",
    "Waffle Potato Fries",
    "Fruit Cup",
    "Mac and Cheese",
    "Chicken Noodle Soup",
    "Frosted Lemonade",
    "Chocolate Chunk Cookie",
    "Vanilla Milkshake",
    "Icedream Cone",
    "Lemonade",
    "Iced Coffee",
    "Sweet Tea",
]

ingredients = [
    'Chicken breast', 'Brioche bun', 'Pickles', 'Spicy chicken breast', 
    'Grilled chicken breast', 'Pepper', 'Tomato', 'Lettuce',
    'Cheddar Cheese', 'Multigrain brioche bun', 'Bacon', 'Colby Jack cheese',
    'Flour tortilla', 'Ranch dressing', 'Breaded chicken nugget',
    'Breaded chicken strip', 'Hard-boiled egg', 'Potato fries', 'Strawberry',
    'Blueberry', 'Mandarin orange', 'Macaroni', 'Cheddar cheese',
    'Parmesean cheese', 'Romano Cheese', 'Chicken broth', 'Carrots',
    'Celery', 'Egg noodles', 'Saltine Cracker', 'Lemonade',
    'Vanilla ice cream', 'Chocolate chunk cookie', 'Milk', 'Whipped cream',
    'Cherry', 'Icecream', 'Sugar cone', 'Sweet tea', 'Coffee brew'
]

inventoryToMenu = {
    "Classic Chicken Sandwich": ["Chicken breast", "Brioche bun", "Pickles"],
    "Spicy Chicken Sandwich": ["Spicy chicken breast", "Brioche bun", "Pickles", "Pepper"],
    "Chicken Deluxe Sandwich": ["Chicken breast", "Brioche bun", "Tomato", "Lettuce", "Cheddar Cheese", "Pickles"],
    "Grilled Chicken Sandwich": ["Grilled chicken breast", "Multigrain brioche bun", "Tomato", "Lettuce"],
    "Grilled Chicken Club Sandwich": ["Grilled chicken breast", "Multigrain brioche bun", "Tomato", "Lettuce", "Bacon", "Colby Jack cheese"],
    "Cool Wrap": ["Grilled chicken breast", "Flour tortilla", "Tomato", "Lettuce", "Colby Jack cheese", "Ranch dressing"],
    "Nuggets": ["Breaded chicken nugget"],
    "Chick-n-Strips": ["Breaded chicken strip"],
    "Cobb Salad": ["Lettuce", "Breaded chicken nugget", "Hard-boiled egg", "Tomato", "Ranch dressing"],
    "Waffle Potato Fries": ["Potato fries"],
    "Fruit Cup": ["Strawberry", "Blueberry", "Mandarin orange"],
    "Mac and Cheese": ["Macaroni", "Cheddar cheese", "Parmesean cheese", "Romano Cheese"],
    "Chicken Noodle Soup": ["Chicken broth", "Chicken breast", "Carrots", "Celery", "Egg noodles", "Saltine Cracker"],
    "Frosted Lemonade": ["Lemonade", "Vanilla ice cream"],
    "Chocolate Chunk Cookie": ["Chocolate chunk cookie"],
    "Vanilla Milkshake": ["Vanilla ice cream", "Milk", "Whipped cream", "Cherry"],
    "Icedream Cone": ["Icecream", "Sugar cone"],
    "Sweet Tea": ["Sweet tea"],
    "Lemonade": ["Lemonade"],
    "Iced Coffee": ["Coffee brew", "Milk"]
}


# Set start and end dates for the sales period
start_date = datetime.date(2022, 2, 27)
end_date = datetime.date(2023, 2, 27)

# Calculate the number of days in the sales period
days_in_period = (end_date - start_date).days + 1

date_list = [start_date + datetime.timedelta(days=x) for x in range(days_in_period)]

# Calculate the total sales goal for the period ($1 million)
sales_goal = 1000000

# Calculate the average daily sales needed to reach the sales goal
average_daily_sales = sales_goal / days_in_period

# Calculate the sales goals for each day in the period, with two game days having higher sales
daily_sales_goals = [average_daily_sales] * days_in_period
game_day_1 = random.randint(14, 120)
game_day_2 = random.randint(180, 240)
daily_sales_goals[game_day_1] = average_daily_sales * 1.5
daily_sales_goals[game_day_2] = average_daily_sales * 2

# Create a list to store the orders data
orders_data = []

customers_ids = np.arange(1, 1001)
staff_ids = np.arange(1, 16)
order_id = 1

# Generate sales data for each day in the sales period
for i, date in enumerate(date_list):
    # Generate a list of distinct menu items for the day
    menu_items_for_day = random.sample(menu, k=random.randint(1, len(menu) - 1))

    # Generate a random number of orders for the day
    num_orders = random.randint(50, 250)

    # Calculate the daily sales goal and sales so far
    daily_sales_goal = daily_sales_goals[i]
    sales_so_far = 0

    # Keep track of the number of orders generated for each menu item
    orders_per_item = {item: 0 for item in menu_items_for_day}

    # Generate orders until the daily sales goal is reached
    while sales_so_far < daily_sales_goal:
        # Randomly select a menu item from the list of items for the day
        item = random.choice(menu_items_for_day)

        # Generate a random price for the item between $1 and $20
        price = round(random.uniform(1, 20), 2)

        customer_id = random.choice(customers_ids)
        staff_id = random.choice(staff_ids)

        # Add the price to the sales so far and update the number of orders for the item
        sales_so_far += price
        orders_per_item[item] += 1

        # Add the order data to the orders_data list
        orders_data.append([
            order_id,
            price,
            datetime.date.strftime(date, '%m/%d/%Y'),
            customer_id,
            staff_id,
        ])
        order_id += 1

    # Print out the total sales for the day
    print(f"Total sales for {datetime.date.strftime(date, '%m/%d/%Y')}: ${sales_so_far:.2f}")

# Open a new CSV file in write mode
with open('./csv/orders.csv', mode='w', newline='') as sales_file:
    # Create a CSV writer object
    sales_writer = csv.writer(sales_file)

    # Write the header row to the CSV file
    sales_writer.writerow(['order_id', 'cost_total', 'date', 'customer_id', 'staff_id'])

    # Write the orders data to the CSV file
    sales_writer.writerows(orders_data)

# Print a message when the sales data has been generated
print("Sales data generated successfully!")