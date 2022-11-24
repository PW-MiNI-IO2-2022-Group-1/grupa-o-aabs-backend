// Copyright Epic Games, Inc. All Rights Reserved.
/*===========================================================================
	Generated code exported from UnrealHeaderTool.
	DO NOT modify this manually! Edit the corresponding .h files instead!
===========================================================================*/

#include "UObject/GeneratedCppIncludes.h"
#include "FPSTest/Public/TerrainMenager.h"
PRAGMA_DISABLE_DEPRECATION_WARNINGS
void EmptyLinkFunctionForGeneratedCodeTerrainMenager() {}
// Cross Module References
	FPSTEST_API UClass* Z_Construct_UClass_ATerrainMenager_NoRegister();
	FPSTEST_API UClass* Z_Construct_UClass_ATerrainMenager();
	ENGINE_API UClass* Z_Construct_UClass_AActor();
	UPackage* Z_Construct_UPackage__Script_FPSTest();
// End Cross Module References
	void ATerrainMenager::StaticRegisterNativesATerrainMenager()
	{
	}
	IMPLEMENT_CLASS_NO_AUTO_REGISTRATION(ATerrainMenager);
	UClass* Z_Construct_UClass_ATerrainMenager_NoRegister()
	{
		return ATerrainMenager::StaticClass();
	}
	struct Z_Construct_UClass_ATerrainMenager_Statics
	{
		static UObject* (*const DependentSingletons[])();
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam Class_MetaDataParams[];
#endif
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_RenderDistance_MetaData[];
#endif
		static const UECodeGen_Private::FUnsizedIntPropertyParams NewProp_RenderDistance;
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_Seed_MetaData[];
#endif
		static const UECodeGen_Private::FUnsizedIntPropertyParams NewProp_Seed;
		static const UECodeGen_Private::FPropertyParamsBase* const PropPointers[];
		static const FCppClassTypeInfoStatic StaticCppClassTypeInfo;
		static const UECodeGen_Private::FClassParams ClassParams;
	};
	UObject* (*const Z_Construct_UClass_ATerrainMenager_Statics::DependentSingletons[])() = {
		(UObject* (*)())Z_Construct_UClass_AActor,
		(UObject* (*)())Z_Construct_UPackage__Script_FPSTest,
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_ATerrainMenager_Statics::Class_MetaDataParams[] = {
		{ "IncludePath", "TerrainMenager.h" },
		{ "ModuleRelativePath", "Public/TerrainMenager.h" },
	};
#endif
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_ATerrainMenager_Statics::NewProp_RenderDistance_MetaData[] = {
		{ "Category", "TerrainMenager" },
		{ "ClampMin", "1" },
		{ "ModuleRelativePath", "Public/TerrainMenager.h" },
	};
#endif
	const UECodeGen_Private::FUnsizedIntPropertyParams Z_Construct_UClass_ATerrainMenager_Statics::NewProp_RenderDistance = { "RenderDistance", nullptr, (EPropertyFlags)0x0010000000000001, UECodeGen_Private::EPropertyGenFlags::Int, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(ATerrainMenager, RenderDistance), METADATA_PARAMS(Z_Construct_UClass_ATerrainMenager_Statics::NewProp_RenderDistance_MetaData, UE_ARRAY_COUNT(Z_Construct_UClass_ATerrainMenager_Statics::NewProp_RenderDistance_MetaData)) };
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_ATerrainMenager_Statics::NewProp_Seed_MetaData[] = {
		{ "Category", "TerrainMenager" },
		{ "ModuleRelativePath", "Public/TerrainMenager.h" },
	};
#endif
	const UECodeGen_Private::FUnsizedIntPropertyParams Z_Construct_UClass_ATerrainMenager_Statics::NewProp_Seed = { "Seed", nullptr, (EPropertyFlags)0x0010000000000001, UECodeGen_Private::EPropertyGenFlags::Int, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(ATerrainMenager, Seed), METADATA_PARAMS(Z_Construct_UClass_ATerrainMenager_Statics::NewProp_Seed_MetaData, UE_ARRAY_COUNT(Z_Construct_UClass_ATerrainMenager_Statics::NewProp_Seed_MetaData)) };
	const UECodeGen_Private::FPropertyParamsBase* const Z_Construct_UClass_ATerrainMenager_Statics::PropPointers[] = {
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UClass_ATerrainMenager_Statics::NewProp_RenderDistance,
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UClass_ATerrainMenager_Statics::NewProp_Seed,
	};
	const FCppClassTypeInfoStatic Z_Construct_UClass_ATerrainMenager_Statics::StaticCppClassTypeInfo = {
		TCppClassTypeTraits<ATerrainMenager>::IsAbstract,
	};
	const UECodeGen_Private::FClassParams Z_Construct_UClass_ATerrainMenager_Statics::ClassParams = {
		&ATerrainMenager::StaticClass,
		"Engine",
		&StaticCppClassTypeInfo,
		DependentSingletons,
		nullptr,
		Z_Construct_UClass_ATerrainMenager_Statics::PropPointers,
		nullptr,
		UE_ARRAY_COUNT(DependentSingletons),
		0,
		UE_ARRAY_COUNT(Z_Construct_UClass_ATerrainMenager_Statics::PropPointers),
		0,
		0x009000A4u,
		METADATA_PARAMS(Z_Construct_UClass_ATerrainMenager_Statics::Class_MetaDataParams, UE_ARRAY_COUNT(Z_Construct_UClass_ATerrainMenager_Statics::Class_MetaDataParams))
	};
	UClass* Z_Construct_UClass_ATerrainMenager()
	{
		if (!Z_Registration_Info_UClass_ATerrainMenager.OuterSingleton)
		{
			UECodeGen_Private::ConstructUClass(Z_Registration_Info_UClass_ATerrainMenager.OuterSingleton, Z_Construct_UClass_ATerrainMenager_Statics::ClassParams);
		}
		return Z_Registration_Info_UClass_ATerrainMenager.OuterSingleton;
	}
	template<> FPSTEST_API UClass* StaticClass<ATerrainMenager>()
	{
		return ATerrainMenager::StaticClass();
	}
	DEFINE_VTABLE_PTR_HELPER_CTOR(ATerrainMenager);
	struct Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_Public_TerrainMenager_h_Statics
	{
		static const FClassRegisterCompiledInInfo ClassInfo[];
	};
	const FClassRegisterCompiledInInfo Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_Public_TerrainMenager_h_Statics::ClassInfo[] = {
		{ Z_Construct_UClass_ATerrainMenager, ATerrainMenager::StaticClass, TEXT("ATerrainMenager"), &Z_Registration_Info_UClass_ATerrainMenager, CONSTRUCT_RELOAD_VERSION_INFO(FClassReloadVersionInfo, sizeof(ATerrainMenager), 92054752U) },
	};
	static FRegisterCompiledInInfo Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_Public_TerrainMenager_h_330534400(TEXT("/Script/FPSTest"),
		Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_Public_TerrainMenager_h_Statics::ClassInfo, UE_ARRAY_COUNT(Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_Public_TerrainMenager_h_Statics::ClassInfo),
		nullptr, 0,
		nullptr, 0);
PRAGMA_ENABLE_DEPRECATION_WARNINGS
