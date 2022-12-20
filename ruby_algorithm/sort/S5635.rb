class Student
  attr_accessor :name, :day, :month, :year

  def initialize(inputs)
    @name = inputs[0]
    @day = inputs[1].to_i
    @month = inputs[2].to_i
    @year = inputs[3].to_i
  end
end

n = gets.chomp().to_i

students = []

(1..n).each do |i|
  inputs = gets.chomp().split(" ")
  students.append(Student.new(inputs))
end

students.sort_by! { |student| [student.year, student.month, student.day] }

puts "#{students.last.name}\n#{students.first.name}"
