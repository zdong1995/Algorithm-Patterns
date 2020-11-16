# Algorithm - Array

## 1. Sort
#### Merge Sort
#### Quick Sort
#### Selection Sort

## 2. Two Sum Pattern
#### 2 Sum - Unsorted
HashMap to store the visited element, for each iteration, check whether the complement in the map.

#### 2 Sum - Sorted
Two pointers from begin and end, decrement right when `sum > target`, increment left when `sum < target`.

#### 2 Sum - All Pairs Indices
HashMap to store number and list of indices, for each iteration, check the complement and enumerate all possible indices pairs of current number pair.

#### 2 Sum - All Unique Pairs
- Solution 1: Sort + Two Pointers, skip duplicates after finding pairs
- Solution 2: HashMap to record `<number, index>`, and Set to deduplicate pair

#### 2 Sum - Greater
Sort + Two Pointers, when sum is greater than target, than for current right, the pair sum with left in range `[left, right)` will all be greater than target.
  
#### 2 Sum - Smaller
Sort + Two Pointers, when sum is smaller than target, than for current left, the pair sum with right in range `(left, right]` will all be smaller than target.

#### 2 Sum - Closest
Sort + Two Pointers, maintain one global min difference, update result if the difference between sum and target is smaller.

#### 2 Sum - Data Structure Design
Optimize `add()`:

- Solution 1: HashMap for counter, add O(1) and find O(n)
- Solution 2: ArrayList + Sort + Two Pointers to find, add O(1) and find O(nlogn)

Optimize `find()`:

- Solution 1: HashMap for counter + Set for all sums, add O(n) and find O(1) 
- Solution 2: ArrayList + Insertion Sort + Two Pointers to find, add O(n) and find O(n)

#### 2 Sum - Difference
- Solution 1: Two Pointers similar to sliding window, guarantee `left < right` and not out of bound
- Solution 2: HashMap and check whether `array[i] + target` or `array[i] - target` in the map

#### 3 Sum
#### 3 Sum - Greater
#### 3 Sum - Closest
#### 4 Sum 
