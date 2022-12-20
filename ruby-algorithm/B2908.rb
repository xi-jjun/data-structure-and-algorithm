nums = gets.chomp().split(" ")
num1 = nums[0].reverse
num2 = nums[1].reverse

if num1 > num2
  puts num1
else
  puts num2
end