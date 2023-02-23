# @param {Integer[]} nums
# @return {Integer[][]}
def three_sum(nums)
  answer = []
  nums.sort!

  for i in 0..(nums.length - 3)
    next if i > 0 && nums[i - 1] == nums[i]

    left, right = i + 1, nums.length - 1

    while left < right do
      sum = nums[left] + nums[right] + nums[i]

      if sum < 0
        left += 1
      elsif sum > 0
        right -= 1
      else
        answer << [nums[left], nums[right], nums[i]]

        while left < right && nums[left] == nums[left + 1] do
          left += 1
        end

        while left < right && nums[right] == nums[right - 1] do
          right -= 1
        end

        left += 1
        right -= 1
      end
    end
  end

  answer
end