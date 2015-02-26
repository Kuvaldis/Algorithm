Given string abc
When search substring a
Then index is 0

Given string abc
When search substring b
Then index is 1

Given string abc
When search substring c
Then index is 2

Given string abc
When search substring d
Then index is -1

Given string abc
When search substring ab
Then index is 0

Given string abc
When search substring bc
Then index is 1

Given string abc
When search substring cd
Then index is -1