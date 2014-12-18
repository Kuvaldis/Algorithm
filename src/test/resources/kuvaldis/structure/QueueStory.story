Given empty integer queue
Then queue size is 0

Given empty integer queue
When enqueue 3
And dequeue
Then dequeued items are 3

Given empty integer queue
When enqueue 3
And dequeue
Then queue size is 0

Given empty integer queue
When enqueue 3
And enqueue 1
And dequeue
And dequeue
Then dequeued items are 3, 1

Given empty integer queue
When enqueue 3
And enqueue 1
And dequeue
And dequeue
Then queue size is 0

Given empty integer queue
When enqueue 3
And enqueue 1
And dequeue
Then dequeued items are 3

Given empty integer queue
When enqueue 3
And enqueue 1
And dequeue
Then queue size is 1

Given empty integer queue
When dequeue
Then empty dequeued items

Given empty integer queue
When dequeue
Then queue size is 0