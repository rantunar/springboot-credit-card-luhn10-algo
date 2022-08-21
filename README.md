# Getting Started

API Lists:

1. /credit-card/add - POST
BODY : {
    "name": "Bob",
    "cardNum": "79927398713",
    "cardLimit": 1000
}
Response: 
{
    "name": "Bob",
    "cardNum": "79927398713",
    "balance": 0,
    "cardLimit": 1000
}
Code:
    200 - Success
    400 - Invalid Request
    Validations:
            1. Credit card numbers may vary in length, up to 19 characters
            2. Credit card numbers will always be numeric
            3. Credit card numbers must follow luhn 10 algorithm
    500 - Internal Server Error

2. /credit-card/get-all - GET
Response:
[
    {
        "cardNum": "79927398713",
        "name": "Bob",
        "balance": 0.00,
        "cardLimit": 1000.00
    }
]
Code:
    200 - Success
    500 - Internal Server Error