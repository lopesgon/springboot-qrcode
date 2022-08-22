# BlockingQueue Demo

A simple SpringBoot app to test in-memory queuing (FIFO).

## Test

Execute the following curl command to enqueue a request body
```
curl --location --request POST 'http://localhost:6799/queue' \
--header 'Content-Type: application/json' \
--data-raw '{
    "value1": "Some test",
    "value2": 4
}'
```