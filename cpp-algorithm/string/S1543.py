doc = input()
search = input()

answer = 0
doc = doc.replace(search, '_')
for c in doc:
    if c == '_':
        answer += 1
print(answer)