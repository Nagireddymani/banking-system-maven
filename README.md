# Test Endpoints (Postman)

|  Method  |   URL                                              | Description                 |
|----------|----------------------------------------------------|-----------------------------|
| POST     | /api/accounts                                      | Create account (JSON body)  |
| GET      | /api/accounts/{accountNumber}                      | Get account details         |
| POST     | /api/accounts/{accountNumber}/deposit?amount=500   | Deposit money               |
| POST     | /api/accounts/{accountNumber}/withdraw?amount=200  | Withdraw money              |

# Example JSON for Creating Account

{
  "accountNumber": "12345",
  "holderName": "John Doe",
  "balance": 1000
}
