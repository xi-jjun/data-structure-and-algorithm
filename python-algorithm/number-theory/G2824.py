import sys

def gcd(a, b):
    if a % b == 0: return b
    else: return gcd(b, a % b)


input = sys.stdin.readline

N = int(input())
a_arr = list(map(int, input().split()))

M = int(input())
b_arr = list(map(int, input().split()))

A = 1
B = 1
for data in a_arr:
    A *= data

for data in b_arr:
    B *= data

if A < B:
    A, B = B, A

answer = str(gcd(A, B))
if len(answer) > 9:
    print(answer[len(answer) - 9:])
else:
    print(answer)
