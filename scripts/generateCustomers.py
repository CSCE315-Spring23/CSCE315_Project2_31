import csv
from faker import Faker
import random

fake = Faker()

# Open a new CSV file for writing
with open('customers.csv', mode='w', newline='') as file:
    writer = csv.writer(file)

    # Write the header row
    writer.writerow(['customer_id', 'name'])

    # Generate 1000 rows of fake customer data
    for i in range(1, 1001):
        customer_id = i
        name = fake.name()

        # Write the row to the CSV file
        writer.writerow([customer_id, name])

print('Done!')
