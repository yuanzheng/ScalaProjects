package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("chars times it occurs") {
    val chars: List[Char] = List('s', 'a', 'a', 's', 'd', 's', 'd')
    val result: List[(Char, Int)] = times(chars)
    assert(result === List(('a',2), ('d',2), ('s',3)))
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode") {
    new TestTrees {
      assert(decode(t1, List(0,1)) === "ab".toList)
    }
  }

  test("encode") {
    new TestTrees {
      assert(encode(t1)("ab".toList) === List(0,1))
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("codeBits gets List Bit") {
    assert(codeBits(List())('a') == List[Bit]() )

    assert(codeBits(List(('a', List[Bit](2,4,1))))('a') == List[Bit](2,4,1) )

    assert(codeBits(List(('a', List[Bit](2,4,1))))('x') == List[Bit]() )

  }

  test("convert test") {
    val input = "ABCDEFGH"
    val tree: CodeTree = createCodeTree(input.toList)

    val result = convert(tree)
    val bits: List[Bit] = quickEncode(tree)(List('C', 'G', 'H'))

    assert(bits === List(0,0,0,1,0,0,1,0,1))
  }

}
