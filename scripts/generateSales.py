import csv
import random
import datetime
import numpy as np

menu_prices = []
with open('./csv/menu.csv') as f:
    for line in f:
        if line.startswith('menu_id'):
            continue
        menu_id, menu_item_name, menu_item_price = line.strip().split(',')
        menu_item_price = float(menu_item_price)
        menu_prices.append(menu_item_price)

orders_data = {}
with open('./csv/menuToOrderWithDates.csv') as f:
    for line in f:
        if line.startswith('menu_id'):
            continue
        menu_id, order_id, quantity, order_date = line.strip().split(',')
        quantity = int(quantity)
        price = menu_prices[int(menu_id) - 1] * quantity
        if order_id in orders_data.keys(): # add to price
            orders_data[order_id][0] += round(price, 2)
        else: # set price and date
            orders_data[order_id] = [round(price, 2), order_date]

customers_ids = np.arange(1, 1001)
staff_ids = np.arange(1, 16)

# Open a new CSV file in write mode
with open('./csv/orders.csv', mode='w', newline='') as sales_file:
    # Create a CSV writer object
    sales_writer = csv.writer(sales_file)

    # Write the header row to the CSV file
    sales_writer.writerow(['order_id', 'cost_total', 'date', 'customer_id', 'staff_id'])

    # Write the orders data to the CSV file
    for order_id, (order_price, order_date) in orders_data.items():
        customer_id = np.random.choice(customers_ids)
        staff_id = np.random.choice(staff_ids)
        sales_writer.writerow([order_id, f'{order_price:.2f}', order_date, customer_id, staff_id])
