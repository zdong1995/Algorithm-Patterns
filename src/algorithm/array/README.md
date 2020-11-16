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
#### 2 Sum - Data Structure Design
#### 2 Sum - Difference
#### 3 Sum
#### 3 Sum - Greater
#### 3 Sum - Closest
#### 4 Sum 
