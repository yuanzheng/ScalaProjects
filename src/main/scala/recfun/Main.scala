package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {

    if (c == 0 || c == r)
      1
    else
      pascal(c-1, r-1) + pascal(c, r-1)
  }
  
  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {

    def countIt(counter: Int, rest: List[Char]): Boolean = {
      if (counter < 0)
        false
      else if (counter > 0 && rest.isEmpty)
        false
      else if (counter == 0 && rest.isEmpty)
        true
      else {
        if (rest.head == '(')
          countIt(counter+1, rest.tail)
        else if (rest.head == ')')
          countIt(counter-1, rest.tail)
        else
          countIt(counter, rest.tail)
      }
    }

    countIt(0, chars)
  }
  
  /**
    * Exercise 3
    *
    * The number of ways to change amount a using n kinds of coins equals
    *   1. the number of ways to change amount a using all but the first kind of coin, plus
    *   2. the number of ways to change amount a - d using all n kinds of coins, where d is the denomination of the first kind of coin.
    *
    * Note:
    * To make change can be divided into two groups: those that do not use any of the first kind of coin,
    * and those that do. Therefore, the total number of ways to make change for some amount is equal to the number of
    * ways to make change for the amount without using any of the first kind of coin, plus the number of ways to make
    * change assuming that we do use the first kind of coin. But the latter number is equal to the number of ways to
    * make change for the amount that remains after using a coin of the first kind.
    *
    * Define the degenerative cases – ie the points at which the recursion stops. These are:
    * If the money is 0 then there is 1 way to do it;
    * If the money is less than 0 then there are 0 ways to change it;
    * If there are 0 coins then there are 0 ways to offer the change.
    *
    * https://cartesianproduct.wordpress.com/tag/recursion/
    * https://mitpress.mit.edu/sicp/full-text/book/book-Z-H-11.html#%_sec_1.2.2
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    def loop(money: Int, coins: List[Int]): Int = {
      if (money < 0 || coins.isEmpty ) 0
      else if (money == 0 ) 1
      else loop(money, coins.tail) + loop(money - coins.head, coins)
    }

    loop(money, coins)
  }

}
