def max_area(height)
  water = 0
  left = 0
  right = height.length - 1

  while left < right do
    min_h = [height[left], height[right]].min
    water = [water, (right - left) * min_h].max

    while height[left] <= min_h && left < right do
      left += 1
    end

    while height[right] <= min_h && left < right do
      right -= 1
    end
  end

  water
end