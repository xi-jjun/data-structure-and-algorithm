def make_number(list)
  numbers = []
  while list != nil
    numbers.push(list.val.to_i)
    list = list.next
  end

  numbers.reverse.join.to_i # numbers 배열을 뒤집고 '' 기준 join 한 후에 integer 로 변환
end

def add_two_numbers(l1, l2)
  num1 = make_number(l1)
  num2 = make_number(l2)

  result = num1 + num2

  result.to_s.split('').reverse.map { |e| e.to_i }
  # result.to_s.split('').reverse.map(&:to_i) # 위 코드를 이와 같이 바꿔줄 수 있음
end