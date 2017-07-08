package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import Math.min

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    k <- Gen.frequency((1, arbitrary[Int]))
    h <- oneOf(const(empty), genHeap)
  } yield insert(k, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  def contains(h: H, x: Int): Boolean = {
    if (isEmpty(h)) false
    else {
      val m = findMin(h)
      if (m == x) true
      else contains(deleteMin(h), x)
    }
  }

  property("contains") = forAll { (h1: H, i: Int) =>
    val h2 = insert(i, h1)
    contains(h2, i)
  }

  property("meldingSorted") = forAll { (h1: H, h2: H) =>
    sorted(meld(h1, h2), List())
  }

  property("meldingMin") = forAll { (h1: H, h2: H) =>
    val m1 = findMin(h1)
    val m2 = findMin(h2)
    val melded = meld(h1, h2)
    val m3 = findMin(melded)
    m3 == m1 || m3 == m2
  }

  property("sorted") = forAll { (h: H, i: Int) =>
    sorted(h, List())
  }

  def sorted(h: H, list: List[Int]): Boolean = {
    if (isEmpty(h)) list match {
      case Nil => true
      case x :: xs => xs.forall(x >= _)
    }
    else {
      val m = findMin(h)
      val h2 = deleteMin(h)
      sorted(h2, m :: list)
    }
  }

  property("insertMin") = forAll { (i: Int, k: Int) =>
    val h = empty
    val h2 = insert(i, h)
    val h3 = insert(k, h2)
    findMin(h3) == min(i, k)
  }

  property("delete") = forAll { i: Int =>
    val h = empty
    val h2 = insert(i, h)
    val h3 = deleteMin(h2)
    isEmpty(h3)
  }

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

}
