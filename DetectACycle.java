import java.io.*;
import java.util.*;

class Node {
  int data;
  Node next;
}

public class Solution {
    static boolean hasCycle(Node head) {
      if (head == null) {
        return false;
      }
      
      Node slow = head;
      Node fast = head.next;
      while (slow != fast) {
          if (fast == null || fast.next == null){
            return false;
          }
          slow = slow.next;
          fast = fast.next.next;
      }
      return true;
  }
}
