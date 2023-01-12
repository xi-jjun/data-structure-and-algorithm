def print_line_by_jump(line, jump)
  (0..line.length).step(jump).each { |i| print line[i] }
end

line = gets.chomp
jump = 2

while line != 'Was it a cat I saw?'
  print_line_by_jump(line, jump)
  puts ""
  line = gets.chomp
  jump += 1
end