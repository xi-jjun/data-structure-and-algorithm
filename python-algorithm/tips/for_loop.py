print(list(range(10))) # [0, 1, ..., 9]
# range는 마지막에서 -1 한 값까지 순회

# 2d arr에서 각 요소 배열의 길이가 고정되어 있다면 for-loop 중첩 필요없이 바로 꺼내서 쓸 수 있음
l = [[1, 2, 3], [2, 6, 59]]
for first, second, third in l:
	print(first, second, third)

# list를 idx와 함께 순회
l = ['a', 'b', 'c']
for idx, value in enumerate(l):
	print(idx, value)

d = { 'name': 'jaejun', 'age': 29 }
for key, value in d.items():
	print(key, value)

# for 간략히
l = [i*i for i in range(10)]
print(l)

# for 2d arr 간략히
# l = [i*j for j in range(5) for i in range(5,8)]
l = [[i * j for j in range(1, 5)] for i in range(5, 10)]
print(l)
for i in l:
	for j in i:
		print(i, j)

# reset 2d array by 1
N = 5
M = 8
m = [[1 for _ in range(M)] for _ in range(N)]
print(m)

N = 3
M = 4
L = 2
visited = [[[1 for _ in range(L)] for _ in range(M)] for _ in range(N)]
print(visited)
