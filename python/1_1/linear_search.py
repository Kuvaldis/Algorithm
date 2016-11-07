def linear_search(a, v):
    for i in range(0, len(a)):
        if a[i] == v:
            return i
    return None


a = [1, 3, 2, 6, 2, 34, 5, 6]
print(linear_search(a, 6))
print(linear_search(a, 7))
