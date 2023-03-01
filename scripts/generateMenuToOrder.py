import csv
import random
import datetime
import numpy as np

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

order_id = 1

start_date = datetime.date(2022, 2, 27)
inc = datetime.timedelta(days=1)

game_days = []
total_revenue = 0

# create a new CSV file
with open('./csv/menu_to_order_with_dates.csv', mode='w', newline='') as file:
    writer = csv.writer(file)
    
    # write the header row
    writer.writerow(['menu_id', 'order_id', 'quantity', 'date'])
    
    day = 0
    while day < 400 or len(game_days) < 2 or total_revenue < 10**6:
        print(day, len(game_days), total_revenue)

        curr_date = start_date + day * inc

        is_game_day = random.random() < 0.01

        if is_game_day:
            num_orders = np.random.randint(900, 1000)
            game_days.append(curr_date)
        else:
            num_orders = np.random.randint(300, 500) 

        for i in range(num_orders):
            # randomly select a menu item and quantity for the order
            menu_item_id = random.randint(1, len(menu))
            quantity = random.randint(1, 5)
            
            # randomly select whether to generate a duplicate order
            is_duplicate = random.random() < 0.2
            
            # write the order to the CSV file
            writer.writerow([menu_item_id, order_id, quantity, curr_date])
            
            total_revenue += quantity * menu[menu_item_id-1][1]
            
            # if this is a duplicate order, select a different menu item and write another order
            if is_duplicate:
                while True:
                    new_menu_item_id = random.randint(1, len(menu))
                    if new_menu_item_id != menu_item_id:
                        break
                new_quantity = random.randint(1, 5)
                writer.writerow([new_menu_item_id, order_id, new_quantity, curr_date])

                total_revenue += new_quantity * menu[new_menu_item_id-1][1]
            
            order_id += 1
        
        day += 1

print(game_days)
print(total_revenue)