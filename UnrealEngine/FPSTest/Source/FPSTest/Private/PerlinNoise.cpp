// Fill out your copyright notice in the Description page of Project Settings.


#include "PerlinNoise.h"
#include <cmath>

FVector2D PerlinNoise::ConstVector(int h)
{
	switch (h % 8)
	{
	case 0:
		return FVector2D(1.0, 1.0);
	case 1:
		return FVector2D(-1.0, 1.0);
	case 2:
		return FVector2D(-1.0, -1.0);
	case 3:
		return FVector2D(1.0, -1.0);
	case 4:
		return FVector2D(1.0, 0.0);
	case 5:
		return FVector2D(-1.0, 0.0);
	case 6:
		return FVector2D(0.0, -1.0);
	default:
		return FVector2D(0.0, 1.0);
	}
}

double PerlinNoise::Noise(double x, double y, int permutation[])
{
	int X = (int)floor(x) & 255;
	int Y = (int)floor(y) & 255;

	double xf = x - floor(x);
	double yf = y - floor(y);

	FVector2D topRight = FVector2D(xf - 1.0, yf - 1.0);
	FVector2D topLeft = FVector2D(xf, yf - 1.0);
	FVector2D bottomRight = FVector2D(xf - 1.0, yf);
	FVector2D bottomLeft = FVector2D(xf, yf);

	double valueTopRight = permutation[(permutation[(X + 1) % 256] + Y + 1) % 256];
	double valueTopLeft = permutation[(permutation[X % 256] + Y + 1) % 256];
	double valueBottomRight = permutation[(permutation[(X + 1) % 256] + Y) % 256];
	double valueBottomLeft = permutation[(permutation[X % 256] + Y) % 256];

	double dotTopRight = topRight.Dot(ConstVector(valueTopRight));
	double dotTopLeft = topLeft.Dot(ConstVector(valueTopLeft));
	double dotBottomRight = bottomRight.Dot(ConstVector(valueBottomRight));
	double dotBottomLeft = bottomLeft.Dot(ConstVector(valueBottomLeft));

	double u = Fade(xf);
	double v = Fade(yf);

	return Lerp(
		Lerp(dotBottomLeft, dotTopLeft, v),
		Lerp(dotBottomRight, dotTopRight, v),
		u
	);
}
double PerlinNoise::Lerp(double a, double b, double t)
{
	return a+t*(b-a);
}

double PerlinNoise::Fade(double x)
{
	return ((6.0 * x - 15.0) * x + 10.0) * x * x * x;
}
