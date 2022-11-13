// Copyright Epic Games, Inc. All Rights Reserved.
/*===========================================================================
	Generated code exported from UnrealHeaderTool.
	DO NOT modify this manually! Edit the corresponding .h files instead!
===========================================================================*/

#include "UObject/GeneratedCppIncludes.h"
#include "FPSTest/Public/Terrain.h"
PRAGMA_DISABLE_DEPRECATION_WARNINGS
void EmptyLinkFunctionForGeneratedCodeTerrain() {}
// Cross Module References
	FPSTEST_API UClass* Z_Construct_UClass_ATerrain_NoRegister();
	FPSTEST_API UClass* Z_Construct_UClass_ATerrain();
	ENGINE_API UClass* Z_Construct_UClass_AActor();
	UPackage* Z_Construct_UPackage__Script_FPSTest();
	ENGINE_API UClass* Z_Construct_UClass_UMaterialInterface_NoRegister();
// End Cross Module References
	void ATerrain::StaticRegisterNativesATerrain()
	{
	}
	IMPLEMENT_CLASS_NO_AUTO_REGISTRATION(ATerrain);
	UClass* Z_Construct_UClass_ATerrain_NoRegister()
	{
		return ATerrain::StaticClass();
	}
	struct Z_Construct_UClass_ATerrain_Statics
	{
		static UObject* (*const DependentSingletons[])();
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam Class_MetaDataParams[];
#endif
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam NewProp_Material_MetaData[];
#endif
		static const UECodeGen_Private::FObjectPropertyParams NewProp_Material;
		static const UECodeGen_Private::FPropertyParamsBase* const PropPointers[];
		static const FCppClassTypeInfoStatic StaticCppClassTypeInfo;
		static const UECodeGen_Private::FClassParams ClassParams;
	};
	UObject* (*const Z_Construct_UClass_ATerrain_Statics::DependentSingletons[])() = {
		(UObject* (*)())Z_Construct_UClass_AActor,
		(UObject* (*)())Z_Construct_UPackage__Script_FPSTest,
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_ATerrain_Statics::Class_MetaDataParams[] = {
		{ "IncludePath", "Terrain.h" },
		{ "ModuleRelativePath", "Public/Terrain.h" },
	};
#endif
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_ATerrain_Statics::NewProp_Material_MetaData[] = {
		{ "Category", "Terrain" },
		{ "ModuleRelativePath", "Public/Terrain.h" },
	};
#endif
	const UECodeGen_Private::FObjectPropertyParams Z_Construct_UClass_ATerrain_Statics::NewProp_Material = { "Material", nullptr, (EPropertyFlags)0x0020080000000001, UECodeGen_Private::EPropertyGenFlags::Object, RF_Public|RF_Transient|RF_MarkAsNative, 1, STRUCT_OFFSET(ATerrain, Material), Z_Construct_UClass_UMaterialInterface_NoRegister, METADATA_PARAMS(Z_Construct_UClass_ATerrain_Statics::NewProp_Material_MetaData, UE_ARRAY_COUNT(Z_Construct_UClass_ATerrain_Statics::NewProp_Material_MetaData)) };
	const UECodeGen_Private::FPropertyParamsBase* const Z_Construct_UClass_ATerrain_Statics::PropPointers[] = {
		(const UECodeGen_Private::FPropertyParamsBase*)&Z_Construct_UClass_ATerrain_Statics::NewProp_Material,
	};
	const FCppClassTypeInfoStatic Z_Construct_UClass_ATerrain_Statics::StaticCppClassTypeInfo = {
		TCppClassTypeTraits<ATerrain>::IsAbstract,
	};
	const UECodeGen_Private::FClassParams Z_Construct_UClass_ATerrain_Statics::ClassParams = {
		&ATerrain::StaticClass,
		"Engine",
		&StaticCppClassTypeInfo,
		DependentSingletons,
		nullptr,
		Z_Construct_UClass_ATerrain_Statics::PropPointers,
		nullptr,
		UE_ARRAY_COUNT(DependentSingletons),
		0,
		UE_ARRAY_COUNT(Z_Construct_UClass_ATerrain_Statics::PropPointers),
		0,
		0x009000A4u,
		METADATA_PARAMS(Z_Construct_UClass_ATerrain_Statics::Class_MetaDataParams, UE_ARRAY_COUNT(Z_Construct_UClass_ATerrain_Statics::Class_MetaDataParams))
	};
	UClass* Z_Construct_UClass_ATerrain()
	{
		if (!Z_Registration_Info_UClass_ATerrain.OuterSingleton)
		{
			UECodeGen_Private::ConstructUClass(Z_Registration_Info_UClass_ATerrain.OuterSingleton, Z_Construct_UClass_ATerrain_Statics::ClassParams);
		}
		return Z_Registration_Info_UClass_ATerrain.OuterSingleton;
	}
	template<> FPSTEST_API UClass* StaticClass<ATerrain>()
	{
		return ATerrain::StaticClass();
	}
	DEFINE_VTABLE_PTR_HELPER_CTOR(ATerrain);
	struct Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_Public_Terrain_h_Statics
	{
		static const FClassRegisterCompiledInInfo ClassInfo[];
	};
	const FClassRegisterCompiledInInfo Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_Public_Terrain_h_Statics::ClassInfo[] = {
		{ Z_Construct_UClass_ATerrain, ATerrain::StaticClass, TEXT("ATerrain"), &Z_Registration_Info_UClass_ATerrain, CONSTRUCT_RELOAD_VERSION_INFO(FClassReloadVersionInfo, sizeof(ATerrain), 2151277385U) },
	};
	static FRegisterCompiledInInfo Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_Public_Terrain_h_84287295(TEXT("/Script/FPSTest"),
		Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_Public_Terrain_h_Statics::ClassInfo, UE_ARRAY_COUNT(Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_Public_Terrain_h_Statics::ClassInfo),
		nullptr, 0,
		nullptr, 0);
PRAGMA_ENABLE_DEPRECATION_WARNINGS
