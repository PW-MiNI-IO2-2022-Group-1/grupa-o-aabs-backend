// Fill out your copyright notice in the Description page of Project Settings.


#include "GenericTree.h"
#include <cmath>
#include "Math/RandomStream.h"
#include "UObject/UObjectGlobals.h"

// Sets default values
AGenericTree::AGenericTree()
{
 	// Set this actor to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	PrimaryActorTick.bCanEverTick = false;
	TrunkSegmentSize = 20.0;
	TreetopSegmentSize = 100.0;
	
	auto cubeMesh = ConstructorHelpers::FObjectFinder<UStaticMesh>(TEXT("StaticMesh'/Engine/BasicShapes/Cube.Cube'")).Object;
	auto cubeMaterial = LoadObject<UMaterial>(nullptr, TEXT("/Game/StarterContent/Materials/M_Wood_Walnut"));
	Root = NewObject<UStaticMeshComponent>(this, TEXT("Root"));
	SetRootComponent(Root);
	Root->SetStaticMesh(cubeMesh);
	Root->SetMaterial(0, cubeMaterial);
	Root->SetMobility(EComponentMobility::Static);
}

void AGenericTree::Initialize(
	int seed,
	// UStaticMeshComponent* trunkStaticMesh,
	// UStaticMeshComponent* treeStaticMesh,
	int minTrunkHeight,
	int maxTrunkHeight,
	int minTreetopHeight,
	int maxTreetopHeight)
{
	
	FRandomStream stream = FRandomStream(seed + GetActorLocation().X + GetActorLocation().Y + GetActorLocation().Z);
	TrunkHeight = stream.RandRange(minTrunkHeight, maxTrunkHeight);
	TreetopHeight = stream.RandRange(minTreetopHeight, maxTreetopHeight);
	double trunkSegmentHeight = Root->GetStaticMesh()->GetBoundingBox().GetSize().Z;
	TrunkSegmentSize = TrunkHeight / round(TrunkHeight / TrunkSegmentSize); // actual size -> nearest value to segment size so height is precise
	auto trunkScaleVector =  FVector(TrunkSegmentSize / trunkSegmentHeight);
	Root->SetWorldScale3D(trunkScaleVector);
	Root->SetRelativeScale3D(trunkScaleVector);

	RenderTrunk();
	RenderTreetop();
}

// Called when the game starts or when spawned
void AGenericTree::BeginPlay()
{
	Super::BeginPlay();
}

void AGenericTree::RenderTrunk()
{
	FVector pos = GetActorLocation();
	double currentHeight = TrunkSegmentSize;

	FActorSpawnParameters spawnParams = FActorSpawnParameters();
	spawnParams.Template = Cast<AActor>(Root);

	while (currentHeight < TrunkHeight) {
		auto newTrunkSegment = DuplicateObject<UStaticMeshComponent>(Root, this);

		if (newTrunkSegment != nullptr)
		{
			FVector newLocation = FVector(
				pos.X,
				pos.Y,
				pos.Z + currentHeight
			);

			if (GEngine != NULL && !newLocation.IsZero()) {
				// GEngine->AddOnScreenDebugMessage(-1, 5.0f, FColor::Red, FString(TEXT("Spawn tree trunk segment at ")).Append(newLocation.ToCompactString()));
			}
			newTrunkSegment->SetWorldLocation(newLocation);
			newTrunkSegment->RegisterComponent();
			Trunk.Add(newTrunkSegment);
			currentHeight += TrunkSegmentSize;
		}
		
	}
}

void AGenericTree::RenderTreetop()
{

}


