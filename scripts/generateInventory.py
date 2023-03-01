import csv
import random
from datetime import datetime


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

header = ["inventory_id", "name", "quantity"]

with open('./csv/inventory.csv', 'w', encoding='UTF8') as f:
    writer = csv.writer(f)

    # write the header
    writer.writerow(header)

    for i, elem in enumerate(ingredients):
        quantity = random.randint(200, 1000)
        data = [i+1, elem, quantity]
        writer.writerow(data)
    