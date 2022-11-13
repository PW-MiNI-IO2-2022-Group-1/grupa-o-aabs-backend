// Fill out your copyright notice in the Description page of Project Settings.

#pragma once

#include "CoreMinimal.h"

static class FPSTEST_API PerlinNoise
{
public:

	static double Noise(double x, double y);

protected:

private:

	static double Lerp(double a, double b, double t);

	static double Fade(double t);

	static FVector2D ConstVector(int h);
	
};

//https://mrl.cs.nyu.edu/~perlin/paper445.pdf <- Ken Perlin article
//https://cs.nyu.edu/~perlin/noise/ <- Ken Perlin's implementation od improved noise (Perlin's Noise)
