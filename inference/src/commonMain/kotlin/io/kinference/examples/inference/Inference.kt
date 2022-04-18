package io.kinference.examples.inference

import io.kinference.core.KIEngine
import io.kinference.core.data.tensor.asTensor
import io.kinference.examples.inference.models.POSTagger
import io.kinference.examples.inference.models.SumModel
import io.kinference.ndarray.arrays.FloatNDArray

//Use engine from kinference-core implementation
val ENGINE = KIEngine
/*
The other ways to load the model:
Model.load(bytes, engine)
Engine.loadModel(bytes)

Engine is the specific implementation from chosen backend (i.e. KIEngine, TFJSEngine)
*/

suspend fun runSum() {
    println("\nSUM MODEL")
    val sumModel = SumModel.create(ENGINE)

    //kinference-core backend data representation
    val left = FloatNDArray(shape=intArrayOf(2, 2)) { it.toFloat() }
    val right = FloatNDArray(shape=intArrayOf(2, 2)) { it.toFloat() }

    //Convert the data to model-readable format before inference
    val output = sumModel.predict(left.asTensor("x"), right.asTensor("y"))
    //extract NDArray data from model output
    val outputNDArray = output.data as FloatNDArray
    val outputArray = outputNDArray.array.toArray()

    println("Sum result:")
    println("[")
    for (row in 0 until outputNDArray.shape[0]) {
        print("\t[")
        for (col in 0 until outputNDArray.shape[1]) {
            print("${outputArray[row * outputNDArray.shape[1] + col]}, ")
        }
        print("]\n")
    }
    println("]")
}

suspend fun runPOSTagger() {
    println("POS TAGGER MODEL")
    val posTagger = POSTagger.create(ENGINE)
    val inputs = POSTagger.getInputsFromResources(ENGINE)

    println("Start predicting:")
    val outputs = posTagger.model.predict(inputs)

    for ((name, tensor) in outputs) {
        println("Name = $name")
        val ndArray = tensor.data as FloatNDArray
        val array = ndArray.array.toArray()
        println("Array size = ${array.size}")
        println("Tensor shape = ${ndArray.shape.joinToString(prefix = "[", postfix = "]")}")
    }
}