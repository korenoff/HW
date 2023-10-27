package main

import "fmt"

func main() {
	list_1 := NewFromSlice([]int{1, 2, 3, 4, 5})
	list_1.PrintLinkedList()
	//1 - 2 - 3 - 4 - 5

	list_1.Add(7)
	list_1.PrintLinkedList()
	//1 - 2 - 3 - 4 - 5 - 7

	list_1.Pop()
	list_1.PrintLinkedList()
	//1 - 2 - 3 - 4 - 5

	ans, _ := list_1.At(3)
	fmt.Println(ans)
	//4

	list_1.UpdateAt(3, 10)
	list_1.PrintLinkedList()
	//1 - 2 - 3 - 10 - 5

	list_1.DeleteFrom(1)
	list_1.PrintLinkedList()
	//1 - 3 - 10 - 5

	fmt.Println(list_1.Size())
	//4
}
