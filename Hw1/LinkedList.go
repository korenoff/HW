package main

import (
	"errors"
	"fmt"
)

type Node struct {
	val  int
	next *Node
}

type LinkedList struct {
	head *Node
}

func New(q int) *LinkedList {
	head := &Node{}
	list := LinkedList{head: head}

	tmp := head

	for i := 1; i < q; i++ {
		newNode := &Node{}
		tmp.next = newNode
		tmp = newNode
	}
	return &list
}

func (l *LinkedList) Add(vall int) {
	newNode := Node{val: vall}
	tmp := l.head
	for tmp.next != nil {
		tmp = tmp.next
	}
	tmp.next = &newNode
}

func (l *LinkedList) Pop() error {
	if l.Size() == 1 {
		l.head = nil
		return nil
	}
	tmp := l.head
	for tmp.next.next != nil {
		tmp = tmp.next
	}
	tmp.next = nil
	return nil
}

func (l *LinkedList) At(pos int) (int, error) {
	if pos < 0 || pos > l.Size() {
		return 0, errors.New("out of range")
	}
	tmp := l.head
	index := 0
	for index < pos {
		tmp = tmp.next
		index++
	}
	return tmp.val, nil
}

func (l *LinkedList) Size() int {
	count := 1
	tmp := l.head
	for tmp.next != nil {
		count++
		tmp = tmp.next
	}
	return count
}

func (l *LinkedList) DeleteFrom(pos int) error {
	if pos < 0 || pos > l.Size() {
		return errors.New("out of range")
	}
	tmp := l.head
	if pos == 0 {
		l.head = tmp.next
		return nil
	}
	if pos == l.Size()-1 {
		newList := l.Pop()
		if newList != nil {
			return newList
		}
	}

	for i := 0; i < pos-1; i++ {
		tmp = tmp.next
	}
	tmp.next = tmp.next.next
	return nil
}

func (l *LinkedList) UpdateAt(pos, val int) error {
	if pos < 0 || pos > l.Size() {
		return errors.New("out of range")
	}
	tmp := l.head
	for i := 0; i < pos; i++ {
		tmp = tmp.next
	}
	tmp.val = val
	return nil
}

func NewFromSlice(s []int) *LinkedList {
	size := len(s)
	list := New(size)
	tmp := list.head
	for i := 0; i < size; i++ {
		tmp.val = s[i]
		tmp = tmp.next
	}
	return list
}

func (l *LinkedList) PrintLinkedList() {
	tmp := l.head
	for tmp.next != nil {
		fmt.Print(tmp.val)
		tmp = tmp.next
		fmt.Print(" - ")
	}
	fmt.Println(tmp.val)
}
