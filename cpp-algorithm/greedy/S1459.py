import sys
x, y, W, S = map(int, sys.stdin.readline().split(" "))
print(min(S * max(x, y) if (x + y) % 2 == 0 else S * (max(x, y) - 1) + W, min(S * min(x, y) + W * (max(x, y) - min(x, y)), W * (x + y))))