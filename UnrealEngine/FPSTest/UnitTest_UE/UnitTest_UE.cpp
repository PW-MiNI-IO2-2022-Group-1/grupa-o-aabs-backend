#include "pch.h"
#include "CppUnitTest.h"
#include "../Source/FPSTest/Public/PerlinNoise.h"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTestUE
{
	TEST_CLASS(UnitTestUE)
	{
	public:
		
		TEST_METHOD(TestMethod1)
		{
			PerlinNoise::Noise(1.0, 1.0);
		}
	};
}
