require 'date'

mon, day = gets.chomp.split(' ')
puts Date.new(2007, mon.to_i, day.to_i).strftime("%A").upcase[0..2]
