import random
import csv
from datetime import *


menu_names = [
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


menu = [
    ["Classic Chicken Sandwich", 3.99],
    ["Spicy Chicken Sandwich", 4.29],
    ["Chicken Deluxe Sandwich", 4.89],
    ["Grilled Chicken Sandwich", 4.69],
    ["Grilled Chicken Club Sandwich", 6.39],
    ["Cool Wrap", 7.19],
    ["Nuggets", 3.55],
    ["Chick-n-Strips", 3.79],
    ["Cobb Salad", 7.19],
    ["Waffle Potato Fries", 2.35],
    ["Fruit Cup", 3.25],
    ["Mac and Cheese", 3.95],
    ["Chicken Noodle Soup", 4.05],
    ["Frosted Lemonade", 3.19],
    ["Chocolate Chunk Cookie", 1.29],
    ["Vanilla Milkshake", 4.25],
    ["Icedream Cone", 1.25],
    ["Lemonade", 2.09],
    ["Iced Coffee", 2.49],
    ["Sweet Tea", 1.85]
]


# Calculate the total sales goal for the period ($1 million)
startDate = date(2022, 2, 25)
sales_goal = 1000000
days_in_period = 365
# Calculate the average daily sales needed to reach the sales goal
average_daily_sales = sales_goal / days_in_period

# Calculate the sales goals for each day in the period, with two game days having higher sales
daily_sales_goals = [average_daily_sales] * days_in_period
game_day_1 = random.randint(14, 120)
game_day_2 = random.randint(180, 240)
daily_sales_goals[game_day_1] = average_daily_sales * 3
daily_sales_goals[game_day_2] = average_daily_sales * 2

# Create a list to store the orders data
orders_data = []
total_sales = 0

# Generate sales data for each day in the sales period
for i in range(days_in_period):


    daily_sales_goal = daily_sales_goals[i]
    sales_so_far = 0
    
    # Keep track of the number of orders generated for each menu item
    orderCount = 0
    # Generate orders until the daily sales goal is reached
    while sales_so_far < daily_sales_goal:

        items_in_order = random.randint(1, 10)
        order_items = []
        order_price = 0
        for j in range(items_in_order):
            itemIndex = random.randint(0, len(menu)-1)

            item = menu[itemIndex][0]
            order_items.append(item)

            price = menu[itemIndex][1]
            order_price += price
            
        sales_so_far += order_price
        

        # Add the order data to the orders_data list
        orders_data.append([
            order_items,
            order_price,
            startDate + timedelta(days=i)
        ])
        orderCount += 1

    # Print out the total sales for the day
    print("Total sales for day {}: {:2f}, total orders for today {}".format(i+1, sales_so_far, orderCount))
    total_sales += sales_so_far

print("Total sales", total_sales)

# Open a new CSV file in write mode
with open('./csv/menuToOrder.csv', mode='w', newline='') as sales_file:
    # Create a CSV writer object
    sales_writer = csv.writer(sales_file)

    # Write the header row to the CSV file
    sales_writer.writerow(['menu_id', 'order_id', 'quantity', 'date'])

    for order_id, elem in enumerate(orders_data):
        seenItems = set()
        for item in elem[0]:
            qty = elem[0].count(item)
            if item in seenItems:
                continue
            seenItems.add(item)
            menu_id = menu_names.index(item) + 1
            d = elem[2]
            sales_writer.writerow([menu_id, order_id+1, qty, d])


# Print a message when the sales data has been generated





