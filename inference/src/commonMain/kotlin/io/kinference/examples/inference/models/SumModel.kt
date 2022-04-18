package io.kinference.examples.inference.models

import io.kinference.InferenceEngine
import io.kinference.data.ONNXData
import io.kinference.examples.utils.Path
import io.kinference.examples.utils.ResourceLoader
import io.kinference.model.Model

class SumModel<T : ONNXData<*, *>>(modelBytes: ByteArray, engine: InferenceEngine<T>) {
    private val model = Model.load(modelBytes, engine)

    fun predict(left: T, right: T): T {
        val output = model.predict(listOf(left, right))
        return output["sum"]!!
    }

    companion object {
        suspend fun <T : ONNXData<*, *>> create(engine: InferenceEngine<T>): SumModel<T> {
            return SumModel(ResourceLoader.bytes(Path("/add/model.onnx")), engine)
        }
    }
}