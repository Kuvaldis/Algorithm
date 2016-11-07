def binary_sum(a, b):
    c = [0] * (len(a) + 1)
    r = 0
    for i in reversed(range(0, len(a))):
        c[i + 1] = a[i] ^ b[i] ^ r
        r = a[i] & b[i] | a[i] & r | b[i] & r
    c[0] = r
    return c

print(binary_sum([0, 1, 1, 0], [1, 1, 0, 1]))