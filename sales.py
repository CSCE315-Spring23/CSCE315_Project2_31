import csv
import random
import datetime


menu_items = [
    "Classic Chicken Sandwich",
    "Spicy Chicken Sandwich",
    "Chicken Deluxe Sandwich",
    "Grilled Chicken Sandwich",
    "Grilled Chicken Club Sandwich",
    "Grilled Chicken Cool Wrap",
    "Chicken Deluxe Cool Wrap",
    "Nuggets",
    "Chick-n-Strips",
    "Cobb Salad",
    "Waffle Potato Fries",
    "Fruit Cup",
    "Mac and Cheese",
    "Chicken Noodle Soup",
    "Frosted Lemonade",
    "Chocolate Chunk Cookie"
    "Vanilla Milkshake",
    "Icedream Cone",
    "Lemonade",
    "Sweet Tea",
]

ingredients = [
    "chicken breast", "spicy chicken breast", "brioche bun", "pickle slices", "butter", "flour", "milk", "salt", "pepper",
    "potatoes", "oil", "vanilla ice cream", "whipped cream", "cherry", "icecream", "biscuit", "yeast roll",
    "multigrain bun", "lettuce", "tomato", "honey mustard sauce", "corn", "black beans", "shredded cheese",
    "tortilla strips", "ranch dressing", "bacon", "egg", "blue cheese", "avocado", "cabbage", "carrots", "onion",
    "mayonnaise", "sugar", "vinegar", "lemonade", "coffee", "vanilla syrup", "egg white patty", "English muffin",
    "cheese", "American cheese", "vanilla Greek yogurt", "fresh berries", "granola"
]

menu_to_inventory = [
    "Chicken Sandwich", ["chicken breast", "brioche bun", "pickle slices"],
    "Spicy Chicken Sandwich", ["spicy chicken breast", "brioche bun", "pickle slices"],
    "Chicken Nuggets", ["frozen nuggets"],
    "Waffle Fries", ["frozen waffle fries"],
    "Milkshake", ["vanilla ice cream", "milk", "whipped cream", "cherry"],
    "Icedream Cone", ["icecream"],
    "Chicken Biscuit", ["chicken breast", "biscuit", "butter"],
    "Chicken Mini", ["chicken breast", "yeast roll", "pickle slices"],
    "Hashbrowns", ["potatoes", "oil", "salt", "pepper"],
    "Grilled Chicken Sandwich", ["chicken breast", "multigrain bun", "lettuce", "tomato", "honey mustard sauce"],
    "Spicy Southwest Salad", ["spicy chicken breast", "lettuce", "corn", "black beans", "tomato", "shredded cheese", "tortilla strips", "ranch dressing"],
    "Cobb Salad", ["grilled chicken breast", "lettuce", "bacon", "egg", "blue cheese", "tomato", "avocado", "ranch dressing"],
    "Chicken Cool Wrap", ["grilled chicken breast", "lettuce", "red cabbage", "carrots", "cheese", "dressing", "flour tortilla"],
    "Fruit Cup", ["fresh fruit"],
    "Mac and Cheese", ["macaroni", "cheese sauce", "milk", "butter"],
    "Cole Slaw", ["cabbage", "carrots", "onion", "mayonnaise", "sugar", "vinegar", "salt", "pepper"],
    "Frosted Lemonade", ["lemonade", "vanilla ice cream"],
    "Frosted Coffee", ["coffee", "vanilla syrup", "vanilla ice cream"],
    "Egg White Grill", ["grilled chicken breast", "egg white patty", "English muffin", "cheese"],
    "Bacon, Egg & Cheese Biscuit", ["biscuit", "bacon", "egg patty", "American cheese"],
    "Grilled Chicken Club Sandwich", ["grilled chicken breast", "multigrain bun", "bacon", "lettuce", "tomato", "cheese", "honey mustard sauce"],
    "Deluxe Chicken Sandwich", ["chicken breast", "brioche bun", "lettuce", "tomato", "mayonnaise"],
    "Grilled Cool Wrap", ["grilled chicken breast", "lettuce", "red cabbage", "carrots", "cheese", "dressing", "flour tortilla"],
    "Greek Yogurt Parfait", ["vanilla Greek yogurt", "fresh berries", "granola"]
]








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

# Generate sales data for each day in the sales period
for i, date in enumerate(date_list):
    # Generate a list of distinct menu items for the day
    menu_items_for_day = random.sample(menu_items, k=random.randint(1, len(menu_items) - 1))

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

        # Add the price to the sales so far and update the number of orders for the item
        sales_so_far += price
        orders_per_item[item] += 1

        # Add the order data to the orders_data list
        orders_data.append([
            datetime.date.strftime(date, '%m/%d/%Y'),
            item,
            price
        ])

    # Print out the total sales for the day
    print(f"Total sales for {datetime.date.strftime(date, '%m/%d/%Y')}: ${sales_so_far:.2f}")

# Open a new CSV file in write mode
with open('restaurant_sales.csv', mode='w', newline='') as sales_file:
    # Create a CSV writer object
    sales_writer = csv.writer(sales_file)

    # Write the header row to the CSV file
    sales_writer.writerow(['date', 'item', 'cost_total'])

    # Write the orders data to the CSV file
    sales_writer.writerows(orders_data)

# Print a message when the sales data has been generated
print("Sales data generated successfully!")