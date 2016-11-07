def sort(a):
    for i in range(1, len(a)):
        val = a[i]
        j = i
        while j > 0 and a[j - 1] > val:
            a[j] = a[j - 1]
            j -= 1
        a[j] = val


def invert_sort(a):
    for i in range(1, len(a)):
        val = a[i]
        j = i
        while j > 0 and a[j - 1] < val:
            a[j] = a[j - 1]
            j -= 1
        a[j] = val


a = [1, 4, 2, 5, 1, 3, 8, 11, 2, 6, 1]
sort(a)
print(a)
a = [1, 4, 2, 5, 1, 3, 8, 11, 2, 6, 1]
invert_sort(a)
print(a)
