// Fill out your copyright notice in the Description page of Project Settings.


#include "SpruceLikeTree.h"

ASpruceLikeTree::ASpruceLikeTree()
{
	TrunkInit.HeightBounds = FVector2D(400, 700);
	TrunkInit.InitialSegmentSize = 35;

	TrunkInit.Material = LoadObject<UMaterial>(
		nullptr,
		TEXT("/Game/Trees/Materials/M_Spruce_Bark")
	);

	TrunkRender.RotationDegreesVariance = 15;
	TrunkRender.HorizontalScalingVariance = 0.2;

	TreetopInit.HeightBounds = FVector2D(300, 400);
	TreetopInit.InitialSegmentSize = 100;
	TreetopInit.VerticalOffsetPercentClamp = FVector2D(0.5, 0.8);
	TreetopInit.Material = LoadObject<UMaterial>(
		nullptr,
		TEXT("/Game/Trees/Materials/M_Spruce_Treetop")
	);

	TreetopRender.HorizontalStretch = 2.3;
	TreetopRender.RotationDegreesVariance = 5;
	TreetopRender.HorizontalScalingVariance = 0.3;
}
