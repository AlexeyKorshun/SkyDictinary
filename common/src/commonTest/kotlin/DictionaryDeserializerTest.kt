/**
 * @author Alexey Korshun on 30.09.2020.
 */
/*
class DictionaryDeserializerTest {

    private val rawString = "[{\"id\":517,\"text\":\"test\",\"meanings\":[{\"id\":9045,\"partOfSpeechCode\":\"n\",\"translation\":{\"text\":\"\\u044d\\u043a\\u0437\\u0430\\u043c\\u0435\\u043d\",\"note\":\"\"},\"previewUrl\":\"\\/\\/d2zkmv5t5kao9.cloudfront.net\\/images\\/7843b72892db15579912e5072bfb2754.jpeg?w=96&h=72\",\"imageUrl\":\"\\/\\/d2zkmv5t5kao9.cloudfront.net\\/images\\/7843b72892db15579912e5072bfb2754.jpeg?w=640&h=480\",\"transcription\":\"t\\u025bst\",\"soundUrl\":\"\\/\\/d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=test\"}]}]"

    @Test
    fun testSize() {
        assertEquals(1, DictionaryDeserializer.parse(rawString).size)
    }

    @Test
    fun testMeaningText() {
        assertEquals("test", DictionaryDeserializer.parse(rawString).first().text)
    }

    @Test
    fun testTranslationsSize() {
        assertEquals(1, DictionaryDeserializer.parse(rawString).first().translations.size)
    }

    @Test
    fun testTranslationsText() {
        assertEquals("экзамен", DictionaryDeserializer.parse(rawString).first().translations.first().translation)
    }
}*/
