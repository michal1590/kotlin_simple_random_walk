fun main() {
    val size = getBoardSize()
    var (board, headX, headY) = initBoard(size)

    println("Init Board")
    showBoard(board)
    println("head $headX $headY")


    val stepsLimit = 5
    var stepCount = 0

    while (stepCount < stepsLimit){

        stepCount++

        val allowedMoves = findRightMoves(board, headX, headY, size)

        if (allowedMoves.size == 0){
            println("Game Over")
            break
        }

        val selectedMove = selectMove(allowedMoves)
        println("Move $selectedMove")
        val out = makeMove(selectedMove, headX, headY)
        headX = out.first
        headY = out.second

        updateBoard(board, headX, headY)
        println("\n\n$stepCount Board")
        println("head $headX $headY")
        showBoard(board)
    }

    finish()
}

fun updateBoard(board: List<IntArray>, headX: Int, headY: Int) {
    board[headY][headX] = 1
}


fun makeMove(selectedMove: String, headX: Int, headY: Int): Pair<Int, Int> {
    return when (selectedMove) {
        "left" -> Pair(headX - 1, headY)
        "right" -> Pair(headX + 1, headY)
        "top" -> Pair(headX, headY - 1)
        else -> Pair(headX, headY + 1)
    }
}

fun selectMove(moves: MutableList<String>): String{
    return moves.random()
}


fun findRightMoves(board: List<IntArray>, headX: Int, headY: Int, size: Int): MutableList<String>{
    val allowedMoves = mutableListOf("left", "right", "top", "bottom")
    if ((headX - 1 <= 0) || (board[headY][headX-1] == 1)){
        allowedMoves.remove("left")
    }

    if (headX + 1 < size){
        if (board[headY][headX+1] != 0) allowedMoves.remove("right")
    } else allowedMoves.remove("right")

    if ((headY - 1 <= 0) || (board[headY-1][headX] == 1)){

        allowedMoves.remove("top")
    }

    if (headY + 1 < size){
        if (board[headY+1][headX] != 0) allowedMoves.remove("bottom")
    } else allowedMoves.remove("bottom")

    return allowedMoves
}

fun getBoardSize(): Int {
    println("Enter board size")
    val size = readLine()?.toIntOrNull() ?: 5
    println("Continue with size: $size")
    return size
}


fun initBoard(size: Int): Triple<List<IntArray>, Int, Int>{
    val board = List(size) {IntArray(size) {0} }

    val xStart = (0 until size).random()
    val yStart = (0 until size).random()
    board[yStart][xStart] = 1
    return Triple(board, xStart, yStart)
}


fun showBoard(board: List<IntArray>) {
//    println("\n\nBoard")
    for (row in board){
        for (element in row){
            print("  ")
            print(element)
        }
        println()
    }
}

fun finish(){
    println("\n\n Press Enter to finish")
    readLine()
}