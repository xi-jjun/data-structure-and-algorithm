fun basicLoop() {
	for (i in 1..5) {
		print("$i,")
	}
	println()
}

fun stepLoop() {
	for (i in 1..20 step 5) {
		print("$i,")
	}
	println()
}

// 10부터 3까지 2씩 작아지는 것
fun downToLoop() {
	for (i in 10 downTo 3 step 2) {
		print("$i,")
	}
	println()
}

fun listLoop() {
	val nums = listOf(1, 2, 3, 4)
	for (num in nums) {
		print("$num,")
	}
	println()
}

fun listWithIdxLoop() {
	val names = listOf("jaejun", "hello", "world")
	for ((idx, name) in names.withIndex()) {
		print("(id=$idx, name=$name), ")
	}
	println()
}

fun mapLoop() {
	val m = mapOf("jaejun" to "asshole", "hello" to "world")
	for ((k, v) in m) {
		print("(key=$k, value=$v), ")
	}
	println()
}

fun main() {
	basicLoop()
	stepLoop()
	downToLoop()
	listLoop()
	listWithIdxLoop()
	mapLoop()
}
