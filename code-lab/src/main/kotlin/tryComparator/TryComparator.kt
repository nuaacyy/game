package tryComparator

class personCompare(
   val  name:String,
   val  age:Int
)

class TryComparator:Comparator<personCompare>{
    override fun compare(o1: personCompare?, o2: personCompare?): Int {
        if (o1 == null || o2 == null){
            return 0
        }
        if (o1.age < o2.age){ // 返回rt>1 就会在排序时交换o1,o2
            return 1
        }else{
            return 0
        }
    }
}

fun tryComparator1(){
    val list = mutableListOf<personCompare>()
    val p1 = personCompare("a1",10)
    val p2 = personCompare("b1",3000)
    val p3 = personCompare("c1",200)
    list.add(p1)
    list.add(p2)
    list.add(p3)
    list.sortWith(TryComparator())
    list.forEach { println(it.age) }
}