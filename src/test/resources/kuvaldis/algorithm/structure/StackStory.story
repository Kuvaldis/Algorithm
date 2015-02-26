Given empty integer stack
Then stack size is 0

Given empty integer stack
When push 3
And pop
Then popped items are 3

Given empty integer stack
When push 3
And pop
Then stack size is 0

Given empty integer stack
When push 3
And push 1
And pop
And pop
Then popped items are 1, 3

Given empty integer stack
When push 3
And push 1
And pop
And pop
Then stack size is 0

Given empty integer stack
When push 3
And push 1
And pop
Then popped items are 1

Given empty integer stack
When push 3
And push 1
And pop
Then stack size is 1

Given empty integer stack
When pop
Then empty popped items

Given empty integer stack
When pop
Then stack size is 0