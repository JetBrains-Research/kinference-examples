package io.kinference.examples.inference.models

import io.kinference.InferenceEngine
import io.kinference.data.ONNXData
import io.kinference.data.ONNXDataType
import io.kinference.examples.utils.Path
import io.kinference.examples.utils.ResourceLoader
import io.kinference.model.Model

class POSTagger<T : ONNXData<*, *>>(modelBytes: ByteArray, engine: InferenceEngine<T>) {
    val model = Model.load(modelBytes, engine)

    companion object {
        suspend fun <T : ONNXData<*, *>> create(engine: InferenceEngine<T>) = POSTagger(ResourceLoader.bytes(Path("pos_tagger/model.onnx")), engine)

        //load data from resources and read it according to the required representation (done by the engine)
        suspend fun <T : ONNXData<*, *>> getInputsFromResources(engine: InferenceEngine<T>): List<T> =
            List(13) {
                val bytes = ResourceLoader.bytes(Path("pos_tagger/input_$it.pb"))
                engine.loadData(bytes, ONNXDataType.ONNX_TENSOR) as T
            }
    }
}